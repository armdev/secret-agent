package com.project.resources.jobs;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.Service;
import com.google.inject.Singleton;
import io.dropwizard.lifecycle.Managed;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Singleton
//@Slf4j
public class SecretAgent
        extends AbstractScheduledService
        implements Managed, Service {

    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("SECRET AGENT runOneIteration");
    }

    @Override
    protected Scheduler scheduler() {
        System.out.println("SECRET AGENT scheduler");
        return Scheduler.newFixedDelaySchedule(1000, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void start() throws Exception {
        //logger.info("SECRET AGENT STARTED");
        System.out.println("SECRET AGENT STARTED");
        this.startAsync();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("SECRET AGENT STOPPED");
        this.stopAsync().awaitTerminated();
    }

}
