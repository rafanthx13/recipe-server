package br.rafanthx13.recipesserver.controller;

import br.rafanthx13.recipesserver.exception.ApiErrors;
import br.rafanthx13.recipesserver.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerAdvice {

    // Intercepta as Exceptions dos controllers e poe na 'ApiErrors'
    // para filtrar somente nossas mensagens de erro
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException( ResponseStatusException ex ){
        return new ResponseEntity(new ApiErrors(ex), ex.getStatus());
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
}


