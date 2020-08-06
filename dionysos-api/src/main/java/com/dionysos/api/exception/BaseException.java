package com.dionysos.api.exception;

import com.dionysos.api.common.model.ErrorModel;

public class BaseException extends RuntimeException {

    protected ErrorModel error;

    protected BaseException(ErrorModel error) {
        super(error.getMessage(),
                null
        );
        this.error = error;
    }

}
