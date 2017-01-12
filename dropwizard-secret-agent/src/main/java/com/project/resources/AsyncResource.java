/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.resources;

import com.google.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.Path;
import com.project.services.AsyncExecutorService;

/**
 *
 * @author armen
 */

@Path("/async")
public class AsyncResource {

    // access http://localhost:9090/async
    @Inject
    private AsyncExecutorService executor;

    @GET
    public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
        executor.provideExecutor().execute(() -> {
            String result = this.calculateTillBillion();
            asyncResponse.resume(result);
        });
    }
    
    private String calculateTillBillion(){
        return "please wait";
    }
    //http://allegro.tech/2014/10/async-rest.html
}
