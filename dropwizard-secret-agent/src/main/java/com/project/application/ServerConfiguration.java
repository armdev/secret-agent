package com.project.application;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ServerConfiguration extends Configuration {

    @NotEmpty
    private String message;

    public String getMessage() {
        return message;
    }

}
