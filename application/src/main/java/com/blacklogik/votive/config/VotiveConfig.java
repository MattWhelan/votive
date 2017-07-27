package com.blacklogik.votive.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class VotiveConfig extends Configuration {
    private ElasticClientFactory elasticClientFactory;

    @JsonProperty("elastic")
    public ElasticClientFactory getElasticClientFactory() {
        return elasticClientFactory;
    }

    public void setElasticClientFactory(ElasticClientFactory elasticClientFactory) {
        this.elasticClientFactory = elasticClientFactory;
    }
}
