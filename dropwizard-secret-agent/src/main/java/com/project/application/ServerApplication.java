package com.project.application;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.project.resources.MainResource;
import com.project.resources.jobs.SecretAgent;
import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {

    // run from console if you have not NetBeans!!!! java -jar target/secret-agent-service.jar server src/main/resources/config.yml
    // access http://localhost:9090/secret
    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        GuiceBundle<ServerConfiguration> guiceBundle = GuiceBundle.<ServerConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(ServerConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);

    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new MainResource(configuration.getMessage()));
        final Managed secretAgent = new SecretAgent();

        environment.lifecycle().manage(secretAgent);

    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }
    //https://github.com/jordansjones/fatassjordan
    //https://github.com/andrew-d/raku/blob/master/src/main/java/io/dunham/raku/RakuApplication.java

}
