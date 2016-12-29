package com.project.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.project.models.SecretModel;

/**
 *
 * @author armenar
 */
public interface SendMessageAPI {

    @GET
    @Path("/secret/message/{message}")
    SecretModel getSecretMessage(@PathParam("message") String message);
    
    
}
