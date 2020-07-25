package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestException(String msg) {
        this(HttpStatus.BAD_REQUEST.value(), msg);
    }

    public BadRequestException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }

}
