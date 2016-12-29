package com.project.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Inject;
import com.project.api.SendMessageAPI;
import feign.Feign;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.project.models.SecretModel;

/**
 *
 * @author armdev
 */
@Path("/feign")
//@Produces(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class FeignResource {

    private final SendMessageAPI sendMessageAPI;
   //http://localhost:9090/feign

    @Inject
    public FeignResource(Feign.Builder feignBuilder) {
        sendMessageAPI = feignBuilder.target(SendMessageAPI.class, "http://localhost:9090");
    }

    @Timed
    @GET
    public String secretMessage() {
        //String message = sendMessageAPI.getSecretMessage("java");
        SecretModel message = sendMessageAPI.getSecretMessage("java");
        return String.format("The service is saying: %s (id: %d)",  message.getContent(), message.getId());
    }

    //http://blog.paralleluniverse.co/2014/05/15/modern-java-pt3/
}
