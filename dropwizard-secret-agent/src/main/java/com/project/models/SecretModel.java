/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author armenar
 */
public class SecretModel {

    private long id;
    private @Length(max = 10)
    String content;

    public SecretModel(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public SecretModel() {
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
