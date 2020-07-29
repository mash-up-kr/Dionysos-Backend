package com.dionysos.api.exception.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorModel {
    private int code;
    private String msg;
    private LocalDateTime timestamp;

    @Builder
    private ErrorModel(int code,
                       String msg,
                       LocalDateTime timestamp) {
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
    }
}
