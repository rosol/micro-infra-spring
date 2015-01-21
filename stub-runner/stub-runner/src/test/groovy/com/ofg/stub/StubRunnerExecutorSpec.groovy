package com.ofg.stub

import com.ofg.stub.mapping.ProjectMetadata
import com.ofg.stub.mapping.StubRepository
import com.ofg.stub.registry.StubRegistry
import com.ofg.stub.server.AvailablePortScanner
import spock.lang.Specification

class StubRunnerExecutorSpec extends Specification {

    static final URL EXPECTED_STUB_URL = new URL('http://localhost:8999')
    static final int AVAILABLE_PORT = 8999

    private StubRegistry registry = Mock(StubRegistry)
    private AvailablePortScanner portScanner = Mock(AvailablePortScanner)
    private StubRepository repository
    private Collection<ProjectMetadata> projects

    def setup() {
        portScanner.nextAvailablePort() >> AVAILABLE_PORT
        repository = new StubRepository(new File('src/test/resources/repository'))
        projects = [new ProjectMetadata('bye', 'com/ofg/bye', 'pl')]
    }

    def 'should provide URL for given stub relative path'() {
        given:
            StubRunnerExecutor executor = new StubRunnerExecutor(portScanner, registry)
        when:
            executor.runStubs(repository, projects)
        then:
            executor.getStubUrlByRelativePath('com/ofg/bye') == EXPECTED_STUB_URL
        cleanup:
            executor.shutdown()
    }

    def 'should throw exception for unknown dependency path'() {
        given:
            StubRunnerExecutor executor = new StubRunnerExecutor(portScanner, registry)
        when:
            executor.runStubs(repository, projects)
            executor.getStubUrlByRelativePath('com/ofg/x')
        then:
            def ex = thrown(UnknownDependencyException)
            ex.message == "Unknown dependency with path 'com/ofg/x'"
    }

}
