package com.dionysos.api.common.dto;

import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ErrorResponseDto {
    private int code;
    private String message;

    @JsonIgnore
    private DionysosAPIErrorCode errorCode;

    @Builder
    private ErrorResponseDto(DionysosAPIErrorCode errorCode, Optional<String> message) {
        this.code = errorCode.getCode();

        if (message != null && message.isPresent()) {
            this.message = message.get();
        } else {
            this.message = errorCode.getMessage();
        }

        this.errorCode = errorCode;
    }
}
