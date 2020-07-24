package com.dionysos.api.exception.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorModel {
    private int code;
    private String msg;
    private LocalDateTime timestamp;
}
