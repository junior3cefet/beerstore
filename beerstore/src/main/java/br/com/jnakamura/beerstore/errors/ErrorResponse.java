package br.com.jnakamura.beerstore.errors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PRIVATE;

@JsonAutoDetect(fieldVisibility = ANY)
@RequiredArgsConstructor(access = PRIVATE)
public class ErrorResponse {

    private final int statusCode;

    private final List<ApiError> errors;

    static ErrorResponse of(HttpStatus httpStatus, List<ApiError> errors) {
        return new ErrorResponse(httpStatus.value(), errors);
    }

    static ErrorResponse of (HttpStatus httpStatus, ApiError error) {
        return new ErrorResponse(httpStatus.value(), Collections.singletonList(error));
    }

    @JsonAutoDetect(fieldVisibility = ANY)
    @AllArgsConstructor
    static class ApiError {
        private String code;
        private String message;
    }


}
