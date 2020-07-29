package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;

public class BaseException extends RuntimeException {

    protected ErrorModel error;

    protected BaseException(ErrorModel error) {
        super(error.getMsg(),
                null
        );
        this.error = error;
    }

}
