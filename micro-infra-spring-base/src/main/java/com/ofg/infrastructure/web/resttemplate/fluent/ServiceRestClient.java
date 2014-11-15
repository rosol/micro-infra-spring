package com.ofg.infrastructure.web.resttemplate.fluent;

import com.ofg.infrastructure.discovery.ServiceResolver;
import org.springframework.web.client.RestOperations;

/**
 * Abstraction over {@link org.springframework.web.client.RestOperations} that provides a fluent API
 * for accessing HTTP resources. It's bound with {@link ServiceResolver} that allows to easily access
 * the microservice collaborators.
 * <p/>
 * You can call a collaborator 'users' defined in microservice descriptor for example named 'microservice.json' as follows
 * <p/>
 * <pre>
 *     {
 *           "prod": {
 *           "this": "foo/bar/registration",
 *           "dependencies": {
 *               "users": "foo/bar/users",
 *               "newsletter": "foo/bar/comms/newsletter",
 *               "confirmation": "foo/bar/security/confirmation"
 *               }
 *           }
 *      }
 * </pre>
 * <p/>
 * in the following manner (example for POST):
 * <p/>
 * serviceRestClient.forService('users').post()
 * .onUrl('/some/url/to/service')
 * .body('<loan><id>100</id><name>Smith</name></loan>')
 * .withHeaders()
 * .contentTypeXml()
 * .andExecuteFor()
 * .aResponseEntity()
 * .ofType(String)
 * <p/>
 * If you want to send a request to the outside world you can also profit from this component as follows (example for google.com):
 * <p/>
 * serviceRestClient.forExternalService().get()
 * .onUrl('http://google.com')
 * .andExecuteFor()
 * .aResponseEntity()
 * .ofType(String)
 *
 * @see <a href="https://github.com/4finance/micro-deps">micro-deps project</a>
 */
public class ServiceRestClient {
    public ServiceRestClient(RestOperations restOperations, ServiceResolver serviceResolver) {
        this.restOperations = restOperations;
        this.serviceResolver = serviceResolver;
    }

    /**
     * Returns fluent api to send requests to given collaborating service
     *
     * @param serviceName - name of collaborating service from microservice configuration file
     * @return builder for the specified HttpMethod
     */
    public HttpMethodBuilder forService(String serviceName) {
        return new HttpMethodBuilder(serviceResolver.fetchUrl(serviceName), restOperations);
    }

    /**
     * Returns fluent api to send requests to external service
     *
     * @return builder for the specified HttpMethod
     */
    public HttpMethodBuilder forExternalService() {
        return new HttpMethodBuilder(restOperations);
    }

    private final RestOperations restOperations;
    private final ServiceResolver serviceResolver;
}
