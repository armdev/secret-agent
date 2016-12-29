package com.project.resources;

import com.google.inject.Inject;  
import com.google.inject.name.Named;

import javax.ws.rs.GET;  
import javax.ws.rs.Path;

/**
 *
 * @author armdev
 */
@Path("/secret")
public class MainResource {
    
    private final String message;

    @Inject
    public MainResource(@Named("message") String message) {
        this.message = message;
    }

    
    @GET
    public String getSecretMessage() {
        return message;
    }
    
}
