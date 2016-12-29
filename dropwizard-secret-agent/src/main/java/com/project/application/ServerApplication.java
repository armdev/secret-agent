package com.project.application;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {

    private GuiceBundle<ServerConfiguration> guiceBundle = null;

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        
             final String[] packageNames = { // Current service
                getClass().getPackage().getName(),               
                "com.project.api",               
                "com.project.models",               
                "com.project.resources",              
                "com.project.resources.jobs",
                "com.project.resources.services"};
        
        guiceBundle = GuiceBundle.<ServerConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(ServerConfiguration.class)                
                .enableAutoConfig(packageNames)
                .build();
        bootstrap.addBundle(guiceBundle);

    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        
        //no need register, because enableAutoConfig .
        //https://github.com/HubSpot/dropwizard-guice


    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }
    
    //old
//     @Override
//    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
//        Feign.Builder feignBuilder = Feign.builder()
//        .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
//        .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
//        .decoder(new JacksonDecoder());
//        
//        environment.jersey().register(new MainResource(configuration.getMessage()));
//        environment.jersey().register(new FeignResource(feignBuilder));
//        
//        final Managed secretAgent = new SecretAgent();
//
//        environment.lifecycle().manage(secretAgent);
//
//}

    // run from console if you have not NetBeans!!!! java -jar target/secret-agent-service.jar server src/main/resources/config.yml
    // access http://localhost:9090/secret
    //http://localhost:9090/feign
    //http://localhost:9090/secret/message/java
    //metrics http://localhost:9091/metrics?pretty=true
    //https://github.com/jordansjones/fatassjordan
    //https://github.com/andrew-d/raku/blob/master/src/main/java/io/dunham/raku/RakuApplication.java
}
