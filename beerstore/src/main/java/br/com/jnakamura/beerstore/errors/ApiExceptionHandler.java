package br.com.jnakamura.beerstore.errors;

import br.com.jnakamura.beerstore.service.exception.BeerAlreadyExistException;
import br.com.jnakamura.beerstore.service.exception.BusinessException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static br.com.jnakamura.beerstore.errors.ErrorResponse.ApiError;
import java.util.List;
import java.util.Locale;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";

    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final MessageSource apiErrorMessageSource; //MESMO NOME DA QUE ESTA EM MESSAGE SOURCE CLASS

    public ApiError toApiError(String code, Locale locale, Object... args) {
        String message;

        try {

            message = apiErrorMessageSource.getMessage(code, args, locale);

        } catch (NoSuchMessageException messageException) {

            LOG.error("Could not find any message with code {} and locale {}", code, locale);

            message = NO_MESSAGE_AVAILABLE;
        }

        return new ApiError(code, message);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException exception, Locale locale) {
        final String errorCode = "generic-1"; //pega o código do api_erros.properties
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale, exception.getValue()));

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValidException(MethodArgumentNotValidException exception, Locale locale) {

        Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

        List<ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);

        return ResponseEntity.badRequest().body(errorResponse);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception, Locale locale) {
        final String errorCode = exception.getCode();
        final HttpStatus status = exception.getStatus();
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale));
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
