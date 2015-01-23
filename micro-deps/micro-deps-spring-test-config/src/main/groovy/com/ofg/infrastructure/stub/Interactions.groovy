package com.ofg.infrastructure.stub

import com.github.tomakehurst.wiremock.client.RequestPatternBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.ofg.infrastructure.discovery.ServiceConfigurationResolver
import com.ofg.stub.StubRunning
import groovy.transform.CompileStatic

class Interactions {

    private final StubRunning stubRunning
    private final ServiceConfigurationResolver configurationResolver
    private final Map<String, WireMock> mocks = [:].withDefault { createNewMock(it) }

    Interactions(ServiceConfigurationResolver configurationResolver, StubRunning stubRunning) {
        this.configurationResolver = configurationResolver
        this.stubRunning = stubRunning
    }

    void verifyThat(String collaboratorName, RequestPatternBuilder requestPatternBuilder) {
        mocks.get(collaboratorName).verifyThat(requestPatternBuilder)
    }

    void verifyThat(String collaboratorName, RequestPatternBuilder requestPatternBuilder, int count) {
        mocks.get(collaboratorName).verifyThat(count, requestPatternBuilder)
    }

    void shutdown() {
        mocks.each { it.value.shutdown() }
    }

    private WireMock createNewMock(String collaboratorName) {
        if (isUnknown(collaboratorName)) {
            throw new UnknownCollaboratorException(collaboratorName)
        }
        URL stubUrl = findCollaboratorStubUrl(collaboratorName)
        return new WireMock(stubUrl.host, stubUrl.port)
    }

    private URL findCollaboratorStubUrl(String collaboratorName) {
        String collaboratorPath = configurationResolver.dependencies[collaboratorName]['path']
        def optionalStubUrl = stubRunning.findStubUrlByRelativePath(collaboratorPath)
        if (optionalStubUrl.present) {
            return optionalStubUrl.get()
        } else {
            throw new MissingStubException(collaboratorName)
        }
    }

    private boolean isUnknown(String collaboratorName) {
        !configurationResolver.dependencies.containsKey(collaboratorName)
    }

}

@CompileStatic
class UnknownCollaboratorException extends RuntimeException {

    UnknownCollaboratorException(String collaboratorName) {
        super("Unknown collaborator name: $collaboratorName")
    }

}

@CompileStatic
class MissingStubException extends RuntimeException {

    MissingStubException(String collaboratorName) {
        super("Missing stub for collaborator name: $collaboratorName")
    }

}
