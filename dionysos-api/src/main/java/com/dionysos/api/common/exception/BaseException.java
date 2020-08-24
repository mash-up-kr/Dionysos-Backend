package com.dionysos.api.common.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    protected ErrorResponseDto error;

    @Builder
    protected BaseException(ErrorResponseDto error) {
        super(error.getMessage(),
                null
        );
        this.error = error;
    }

}
