package com.ofg.infrastructure.metrics.registry;

import com.codahale.metrics.*;

/**
 * Custom implementation of {@link MetricRegistry} that prepends if necessary
 * 4finance path prefix to a given metric name.
 *
 * @see com.ofg.infrastructure.metrics.registry.MetricPathProvider
 */
public class PathPrependingMetricRegistry extends MetricRegistry {
    public PathPrependingMetricRegistry(MetricPathProvider metricPathProvider) {
        this.metricPathProvider = metricPathProvider;
    }

    @Override
    public Counter counter(String metricName) {
        return super.counter(metricPathProvider.getMetricPath(metricName));
    }

    @Override
    public Histogram histogram(String metricName) {
        return super.histogram(metricPathProvider.getMetricPath(metricName));
    }

    @Override
    public Timer timer(String metricName) {
        return super.timer(metricPathProvider.getMetricPath(metricName));
    }

    @Override
    public boolean remove(String metricName) {
        return super.remove(metricPathProvider.getMetricPath(metricName));
    }

    @Override
    public Meter meter(String metricName) {
        return super.meter(metricPathProvider.getMetricPath(metricName));
    }

    private final MetricPathProvider metricPathProvider;
}
