package com.ofg.infrastructure.metrics.registry;

import com.google.common.base.Joiner;
import groovy.transform.Memoized;

/**
 * This class is responsible for preparing your metric full path. It also verifies whether your metric name is already
 * <p/>
 * We have a following template for metrics:
 * <p/>
 * <pre>
 *     (root-name).(environment).(country).(application-name).(metric-name)
 * </pre>
 * <p/>
 * for example:
 * <p/>
 * <pre>
 *     apps.test.pl.bluecash-adapter.transfer.request.balance.count
 * </pre>
 */
public class MetricPathProvider {
    public MetricPathProvider(String rootName, String environment, String country, String appName) {
        this.rootName = rootName;
        this.environment = environment;
        this.country = country;
        this.appName = appName;
    }

    @Memoized
    private String getRootMetricPath() {
        return Joiner.on(".").join(rootName, environment, country, appName);
    }

    public String getMetricPath(String metricName) {
        if (isPathPrepended(metricName)) {
            return metricName;
        }

        return Joiner.on(".").join(getRootMetricPath(), metricName);
    }

    public boolean isPathPrepended(String metricName) {
        return metricName.startsWith(getRootMetricPath());
    }

    private final String environment;
    private final String rootName;
    private final String country;
    private final String appName;
}
