package com.dionysos.api.common.exception;

import com.dionysos.api.common.model.ErrorModel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    protected ErrorModel error;

    @Builder
    protected BaseException(ErrorModel error) {
        super(error.getMessage(),
                null
        );
        this.error = error;
    }

}
