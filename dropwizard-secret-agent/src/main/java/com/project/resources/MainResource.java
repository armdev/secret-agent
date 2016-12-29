package com.project.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.project.models.SecretModel;
import com.project.services.CounterService;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author armdev
 */
@Path("/secret")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {

    private final CounterService counterService;
    
    private final String message;     
    

    @Inject
    public MainResource(@Named("message") String message, CounterService counterService) {
        this.message = message;
        this.counterService = counterService;
    }

    @Timed // monitor timing of this service with Metrics
    @GET
    @Path("/message/{message}")
    public SecretModel saySecret(@PathParam("message") Optional<String> name) throws InterruptedException {
        System.out.println("Received message " + name);
        final String value = String.format("SecretService", name.orElse(name.get()));
        Thread.sleep(ThreadLocalRandom.current().nextInt(10, 500));
        return new SecretModel(counterService.next(), value);
    }

    @GET
    public String getSecretMessage() {
        return message;
    }

}
