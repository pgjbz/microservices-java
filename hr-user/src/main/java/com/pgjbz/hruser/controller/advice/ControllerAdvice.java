package com.pgjbz.hruser.controller.advice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    private static final String BAD_REQUEST_TEXT = "Bad request";
    private static final String NOT_ALLOWED_TEXT = "Method not allowed";
    private static final String INTERNAL_SERVER_ERROR_TEXT = "Unexpected error";
    private static final String NOT_FOUND_TEXT = "Not found";

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> badRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    @SneakyThrows
    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> notFound(NoResultException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(NOT_FOUND_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> badRequest(MissingServletRequestParameterException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> notAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(NOT_ALLOWED_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StandardError> badRequest(Exception ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Unexpected error occurred", ex);
        return ResponseEntity.status(httpStatus).body(StandardError.builder()
                .error(INTERNAL_SERVER_ERROR_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build());
    }

    //

}
