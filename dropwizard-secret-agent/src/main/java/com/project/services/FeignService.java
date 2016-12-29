package com.project.services;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;
import javax.inject.Singleton;

@Singleton
public class FeignService {

     private Feign.Builder feignBuilder;    
     
     public Feign.Builder getClient(){     
         return feignBuilder = Feign.builder()
                .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
                .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
                .decoder(new JacksonDecoder());
     }
      
}
