package br.com.jnakamura.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerNotFoundException extends BusinessException {

    public BeerNotFoundException() {
        super("beer-7", HttpStatus.NOT_FOUND);
    }
}
