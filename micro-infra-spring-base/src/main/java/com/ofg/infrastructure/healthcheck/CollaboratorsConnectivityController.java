package com.ofg.infrastructure.healthcheck;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.ofg.infrastructure.discovery.ServiceResolver;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
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
        Collection<String> transformedCollaborators = Collections2.transform(collaborators, new Function<String, String>() {
            @Override
            public String apply(String collaborator) {
                return "\"" + collaborator + "\":" + checkConnectionStatus(collaborator) + "\"";
            }
        });
        return buildCollaboratorsJson(transformedCollaborators);
    }

    private String buildCollaboratorsJson(Collection<String> transformedCollaborators) {
        return "{" + Joiner.on(",").join(transformedCollaborators) + "}";
    }

    private String checkConnectionStatus(String serviceName) {
        String pingResult = pingService(serviceName);
        log.info("Connection status checked for service " + serviceName + " with result: \'" + pingResult + "\'");
        return pingResult.equals("OK") ? "CONNECTED" : "DISCONNECTED";
    }

    private String pingService(final String serviceName) {
        try {
            return serviceRestClient.forService(serviceName).get().onUrl("/ping").anObject().ofType(String.class);
        } catch (Exception e) {
            log.error("Unable to ping service \'" + serviceName + "\'!", e);
            return "ERROR";
        }

    }

    private final ServiceResolver serviceResolver;
    private final ServiceRestClient serviceRestClient;
}
