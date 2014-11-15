package com.ofg.infrastructure.healthcheck;

import com.ofg.infrastructure.discovery.ServiceResolver;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import groovy.json.JsonBuilder;
import groovy.lang.Closure;
import groovy.transform.CompileStatic;
import groovy.transform.PackageScope;
import groovy.util.logging.Slf4j;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * {@link RestController} providing connection state with services the microservice depends upon.
 */
@RestController
class CollaboratorsConnectivityController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    public CollaboratorsConnectivityController(ServiceRestClient serviceRestClient, ServiceResolver serviceResolver) {
        this.serviceRestClient = serviceRestClient;
        this.serviceResolver = serviceResolver;
    }

    /**
     * Returns information about connection status of microservice with other microservices.
     * For properly connected service <b>CONNECTED</b> state is provided and <b>DISCONNECTED</b> otherwise.
     *
     * @return connection status
     */
    @RequestMapping(value = "/collaborators", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCollaboratorsConnectivityInfo() {
        Set<String> collaborators = serviceResolver.fetchCollaboratorsNames();
        Map collaboratorsState = DefaultGroovyMethods.collectEntries(collaborators, new Closure<LinkedHashMap>(this, this) {
            public LinkedHashMap doCall(String collaborator) {
                LinkedHashMap map = new LinkedHashMap(1);
                map.put(, checkConnectionStatus(collaborator));
                return map;
            }

        });
        JsonBuilder json = new JsonBuilder();
        json.call(collaboratorsState);
        return json.toString();
    }

    private String checkConnectionStatus(String serviceName) {
        String pingResult = pingService(serviceName);
        log.info("Connection status checked for service " + serviceName + " with result: \'" + pingResult + "\'");
        return pingResult.equals("OK") ? "CONNECTED" : "DISCONNECTED";
    }

    private String pingService(final String serviceName) {
        try {
            return serviceRestClient.forService(serviceName).get().onUrl("/ping").andExecuteFor().anObject().ofType(String.class);
        } catch (Exception e) {
            log.error("Unable to ping service \'" + serviceName + "\'!", e);
            return "ERROR";
        }

    }

    private final ServiceResolver serviceResolver;
    private final ServiceRestClient serviceRestClient;
}
