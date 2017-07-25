package com.blacklogik.votive;

import com.blacklogik.votive.config.VotiveConfig;
import com.blacklogik.votive.resources.ElectionResource;
import com.blacklogik.votive.services.ElectionService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        environment.jersey().register(new ElectionResource(new ElectionService()));
    }
}
