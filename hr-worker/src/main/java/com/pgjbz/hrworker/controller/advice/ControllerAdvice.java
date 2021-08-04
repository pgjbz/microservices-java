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

    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public StandardError notFound(NoResultException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String error = "Not found";
        return StandardError.builder()
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public StandardError badRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String error = "Bad request";
        return StandardError.builder()
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public StandardError badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String error = "Bad request";
        return StandardError.builder()
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public StandardError badRequest(MissingServletRequestParameterException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String error = "Bad request";
        return StandardError.builder()
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    public StandardError badRequest(Exception ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String error = "Unexpected error";
        log.error("Unexpected error occurred", ex);
        return StandardError.builder()
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .time(LocalDateTime.now())
                .status(httpStatus.value())
                .build();
    }

}
