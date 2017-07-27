package com.blacklogik.votive;

import com.blacklogik.votive.checks.ElasticHealthcheck;
import com.blacklogik.votive.config.VotiveConfig;
import com.blacklogik.votive.dao.ElectionDao;
import com.blacklogik.votive.resources.ElectionResource;
import com.blacklogik.votive.services.ElectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.elasticsearch.client.Client;

public class VotiveApp extends Application<VotiveConfig> {
    public static void main(String[] args) throws Exception {
        new VotiveApp().run(args);
    }

    @Override
    public String getName() {
        return "votive-app";
    }

    @Override
    public void initialize(Bootstrap<VotiveConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    public void run(VotiveConfig votiveConfig, Environment environment) throws Exception {
        Client client = votiveConfig.getElasticClientFactory().build();

        ObjectMapper objectMapper = environment.getObjectMapper();
        ElectionDao electionDao = new ElectionDao(client, objectMapper);
        ElectionService electionService = new ElectionService(electionDao);
        environment.jersey().register(new ElectionResource(electionService));

        environment.healthChecks().register("elasticsearch", new ElasticHealthcheck(client));
    }
}
