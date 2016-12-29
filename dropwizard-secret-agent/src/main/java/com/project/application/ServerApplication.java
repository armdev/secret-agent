package com.project.application;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.project.resources.FeignResource;
import com.project.resources.MainResource;
import com.project.resources.jobs.SecretAgent;
import feign.Feign;
import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import feign.jaxrs.*;
import feign.jackson.*;

public class ServerApplication extends Application<ServerConfiguration> {

    // run from console if you have not NetBeans!!!! java -jar target/secret-agent-service.jar server src/main/resources/config.yml
    // access http://localhost:9090/secret
    //http://localhost:9090/feign
    //http://localhost:9090/secret/message/java
    //metrics http://localhost:9091/metrics?pretty=true
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
        Feign.Builder feignBuilder = Feign.builder()
        .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
        .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
        .decoder(new JacksonDecoder());
        
        environment.jersey().register(new MainResource(configuration.getMessage()));
        environment.jersey().register(new FeignResource(feignBuilder));
        
        final Managed secretAgent = new SecretAgent();

        environment.lifecycle().manage(secretAgent);

    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }
    //https://github.com/jordansjones/fatassjordan
    //https://github.com/andrew-d/raku/blob/master/src/main/java/io/dunham/raku/RakuApplication.java

}
