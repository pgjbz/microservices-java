package com.pgjbz.hrworker.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private static final String INTERNAL_SERVER_ERROR_TEXT = "Unexpected error";
    private static final String NOT_FOUND_TEXT = "Unexpected error";

    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public StandardError notFound(NoResultException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return StandardError.builder()
                .error(NOT_FOUND_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public StandardError badRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public StandardError badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public StandardError badRequest(MissingServletRequestParameterException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return StandardError.builder()
                .error(BAD_REQUEST_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    public StandardError badRequest(Exception ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Unexpected error occurred", ex);
        return StandardError.builder()
                .error(INTERNAL_SERVER_ERROR_TEXT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

}
