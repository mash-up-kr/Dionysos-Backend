package com.dionysos.api.common.model;

import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorModel {
    private int code;
    private String message;
    private LocalDateTime timestamp;

    @JsonIgnore
    private DionysosAPIErrorCode errorCode;

    @Builder
    private ErrorModel(DionysosAPIErrorCode errorCode,
                       LocalDateTime timestamp
    ) {
        this.code = errorCode.getCode();
        this.message = errorCode.getDescription();
        this.timestamp = timestamp;

        this.errorCode = errorCode;
    }
}
