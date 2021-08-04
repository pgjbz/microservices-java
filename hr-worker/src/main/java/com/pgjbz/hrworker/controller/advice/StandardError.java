package com.pgjbz.hrworker.controller.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Getter
@Builder
public class StandardError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private final LocalDateTime time;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public StandardError(LocalDateTime time, Integer status, String error, String message, String path) {
        this.time = requireNonNull(time, "time is mandatory");
        this.status = requireNonNull(status, "status is mandatory");
        this.error = requireNonNull(error, "error is mandatory");
        this.message = requireNonNull(message, "message is mandatory");
        this.path = requireNonNull(path, "path is mandatory");
    }
}
