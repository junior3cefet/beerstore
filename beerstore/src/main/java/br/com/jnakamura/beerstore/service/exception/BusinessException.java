package br.com.jnakamura.beerstore.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException{

    private final String code;

    private final HttpStatus status;

}
