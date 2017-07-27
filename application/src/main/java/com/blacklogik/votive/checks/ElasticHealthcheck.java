package com.blacklogik.votive.checks;

import com.codahale.metrics.health.HealthCheck;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;

import static org.elasticsearch.cluster.health.ClusterHealthStatus.RED;

public class ElasticHealthcheck extends HealthCheck {
    private Client client;

    public ElasticHealthcheck(Client client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        ClusterHealthResponse clusterHealthResponse = client.admin().cluster().prepareHealth().get();
        if (!RED.equals(clusterHealthResponse.getStatus())) {
            return Result.healthy(clusterHealthResponse.getStatus().toString());
        } else {
            return Result.unhealthy(clusterHealthResponse.toString());
        }
    }
}
