package com.project.resources.jobs;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.Service;
import com.google.inject.Singleton;
import io.dropwizard.lifecycle.Managed;
import java.util.concurrent.TimeUnit;

@Singleton
//@Slf4j
public class SecretAgent
        extends AbstractScheduledService
        implements Managed, Service {

    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("SECRET AGENT: I am downloading the internet!");
    }

    @Override
    protected Scheduler scheduler() {
        System.out.println("SECRET AGENT: I am starting again");
        return Scheduler.newFixedDelaySchedule(2000, 4000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void start() throws Exception {
        //logger.info("SECRET AGENT STARTED");
        System.out.println("SECRET AGENT: STARTED");
        this.startAsync();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("SECRET AGENT:  STOPPED");
        this.stopAsync().awaitTerminated();
    }

}
