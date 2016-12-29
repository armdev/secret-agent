/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.application;

import com.google.inject.Binder;  
import com.google.inject.Module;  
import com.google.inject.Provides;

import javax.inject.Named;

public class ServerModule implements Module {  
    
    @Override
    public void configure(Binder binder) {
    }

    @Provides
    @Named("message")
    public String provideMessage(ServerConfiguration serverConfiguration) {
        return serverConfiguration.getMessage();
    }
    
}
