package com.ofg.infrastructure.stub

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.RequestPatternBuilder
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy
import com.github.tomakehurst.wiremock.client.VerificationException
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.http.RequestMethod
import com.google.common.base.Optional
import com.ofg.infrastructure.discovery.ServiceConfigurationResolver
import com.ofg.infrastructure.discovery.web.HttpMockServer
import com.ofg.stub.StubRunning
import groovyx.net.http.HTTPBuilder
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Unroll

import static com.ofg.infrastructure.base.dsl.WireMockHttpRequestMapper.wireMockGet
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse

class InteractionsSpec extends Specification {

    static final String UNKOWN_COLLABORATOR = 'unknown collaborator'
    static final String PING = 'ping collaborator'
    static final String PONG = 'pong collaborator'
    static final String UNKNOWN_PING_PATH = 'com/ofg/unknown/ping'
    static final String KNOWN_PONG_PATH = 'com/ofg/pong'
    static final int MOCK_PORT = 8993
    static final String LOCALHOST = "localhost"
    static final GString PONG_STUB_URL = "http://$LOCALHOST:$MOCK_PORT"
    static final String PONG_ENDPOINT = '/pong'

    @AutoCleanup('shutdownServer') HttpMockServer mockServer
    @AutoCleanup('shutdown') WireMock wireMock
    StubRunning stubRunning
    ServiceConfigurationResolver configurationResolver

    def setup() {
        stubRunningWithPredefinedPongPath()
        configurationResolverWithPingPongDependencies()

        mockServer = new HttpMockServer(MOCK_PORT)
        mockServer.start()

        wireMock = new WireMock(LOCALHOST, mockServer.port())
        wireMock.resetToDefaultMappings()
    }

    def 'should successfully verify interaction with stub'() {
        given:
            predefinedPongInteraction()
            simulatedSinglePongInteraction()
        when:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
            interactions.verifyThat(PONG, expectedRequest())
        then:
            noExceptionThrown()
        cleanup:
            interactions.shutdown()
    }

    def 'should throw verification exception if no verification happened'() {
        given:
            predefinedPongInteraction()
        when:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
            interactions.verifyThat(PONG, expectedRequest())
        then:
            thrown(VerificationException)
        cleanup:
            interactions.shutdown()
    }

    def 'should successfully verify multiple interactions with stub'() {
        given:
            predefinedPongInteraction()
            multiplePongInteractions(3)
        when:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
            interactions.verifyThat(PONG, expectedRequest(), 3)
        then:
            noExceptionThrown()
        cleanup:
            interactions.shutdown()
    }

    @Unroll('should throw exception when there were #actualCount interactions while #verificationCount were expected')
    def 'should throw exception on mismatch between actual and expected interactions count'() {
        given:
            predefinedPongInteraction()
            multiplePongInteractions(3)
        when:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
            interactions.verifyThat(PONG, expectedRequest(), 2)
        then:
            thrown(VerificationException)
        cleanup:
            interactions.shutdown()
        where:
            actualCount | verificationCount
            3           | 5
            5           | 4
    }

    def 'should throw exception for unknown collaborator name'() {
        given:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
        when:
            interactions.verifyThat(UNKOWN_COLLABORATOR, requestPattern())
        then:
            def ex = thrown(UnknownCollaboratorException)
            ex.message == "Unknown collaborator name: $UNKOWN_COLLABORATOR"
        cleanup:
            interactions.shutdown()
    }

    def 'should throw exception for missing stub URL of well-known collaborator name'() {
        given:
            Interactions interactions = new Interactions(configurationResolver, stubRunning)
        when:
            interactions.verifyThat(PING, requestPattern())
        then:
            def ex = thrown(MissingStubException)
            ex.message == "Missing stub for collaborator name: $PING"
        cleanup:
            interactions.shutdown()
    }

    private void configurationResolverWithPingPongDependencies() {
        configurationResolver = Mock(ServiceConfigurationResolver)
        configurationResolver.dependencies >> [
                (PING): [
                        'path': (UNKNOWN_PING_PATH)
                ],
                (PONG): [
                        'path': (KNOWN_PONG_PATH)
                ]
        ]
    }

    private void stubRunningWithPredefinedPongPath() {
        stubRunning = Mock(StubRunning)
        stubRunning.findStubUrlByRelativePath(KNOWN_PONG_PATH) >> Optional.of(new URL(PONG_STUB_URL))
        stubRunning.findStubUrlByRelativePath(_ as String) >> Optional.absent()
    }

    private RequestPatternBuilder requestPattern() {
        Mock(RequestPatternBuilder)
    }

    private void simulatedSinglePongInteraction() {
        def http = new HTTPBuilder(PONG_STUB_URL)
        http.get(path: PONG_ENDPOINT)
        http.shutdown()
    }

    private void multiplePongInteractions(int interactionsCount) {
        interactionsCount.times { simulatedSinglePongInteraction() }
    }

    private predefinedPongInteraction() {
        stubInteraction(wireMockGet(PONG_ENDPOINT), aResponse().withStatus(200))
    }

    private RequestPatternBuilder expectedRequest() {
        def matchingPongEndpoint = new UrlMatchingStrategy()
        matchingPongEndpoint.setUrlPath(PONG_ENDPOINT)
        return new RequestPatternBuilder(RequestMethod.GET, matchingPongEndpoint)
    }

    private void stubInteraction(MappingBuilder mapping, ResponseDefinitionBuilder response) {
        wireMock.register(mapping.willReturn(response))
    }

}
