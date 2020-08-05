package com.dionysos.api.exception;

import com.dionysos.api.common.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(String message) {
        this(HttpStatus.UNAUTHORIZED.value(),
                message
        );
    }

    public UnAuthorizedException(int code,
                                 String message
    ) {
        super(ErrorModel.builder()
                .code(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }
}