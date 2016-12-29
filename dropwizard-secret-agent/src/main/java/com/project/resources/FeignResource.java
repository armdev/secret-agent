package com.project.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.project.api.SendMessageAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.project.models.SecretModel;
import com.project.services.FeignService;

/**
 *
 * @author armdev
 */
@Path("/feign")
@Produces(MediaType.APPLICATION_JSON)
public class FeignResource {

    private final SendMessageAPI sendMessageAPI;
    private final FeignService feignService;
  
    @Inject
    public FeignResource(FeignService feignService) {
      sendMessageAPI = feignService.getClient().target(SendMessageAPI.class, "http://localhost:9090");
      this.feignService = feignService;     
    }
    
    //old
//     @Inject
//    public FeignResource(Feign.Builder feignBuilder) {
//        sendMessageAPI = feignBuilder.target(SendMessageAPI.class, "http://localhost:9090");
//}

    @Timed
    @GET
    public String secretMessage() {
      
        SecretModel message = sendMessageAPI.getSecretMessage("java");
        return String.format("The service is returned: %s (id: %d)",  message.getContent(), message.getId());
    }

    //http://blog.paralleluniverse.co/2014/05/15/modern-java-pt3/
}
