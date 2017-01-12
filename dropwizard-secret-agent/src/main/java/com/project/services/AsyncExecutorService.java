/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

/**
 *
 * @author Admin
 */
@Singleton
public class AsyncExecutorService {

    public ExecutorService provideExecutor() {
        return Executors.newFixedThreadPool(500);
    }
}
