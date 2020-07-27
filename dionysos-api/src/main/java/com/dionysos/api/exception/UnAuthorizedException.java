package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(String msg) {
        this(HttpStatus.UNAUTHORIZED.value(),
                msg
        );
    }

    public UnAuthorizedException(int code,
                                 String msg
    ) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }
}