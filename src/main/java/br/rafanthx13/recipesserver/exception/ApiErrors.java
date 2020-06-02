package br.rafanthx13.recipesserver.exception;

import org.springframework.web.server.ResponseStatusException;

public class ApiErrors {

    public String error;

    public ApiErrors(ResponseStatusException ex) {
        this.error = ex.getReason();
    }

    public ApiErrors(String message){
        this.error = message;
    }
}
