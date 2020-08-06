package com.dionysos.api.common.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorModel {
    private int code;
    private String message;
    private LocalDateTime timestamp;

    @Builder
    private ErrorModel(int code,
                       String message,
                       LocalDateTime timestamp
    ) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
}
