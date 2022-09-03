package com.bustracker.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .msg(e.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<?> handleRemittanceInvalidException(ResourceNotFoundException e, WebRequest request) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .msg("ResourceNotFound Exception: " + e.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public final ResponseEntity<?> handleRemittanceInvalidException(org.springframework.security.core.AuthenticationException e, WebRequest request) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .msg("AuthenticationException Exception: " + e.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<?> handleRemittanceInvalidException(BadCredentialsException e, WebRequest request) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .msg("BadCredentialsException Exception: " + e.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
