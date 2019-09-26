package br.com.jnakamura.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException {


    public BeerAlreadyExistException() {
        super("beer-6", HttpStatus.BAD_REQUEST);
    }
}
