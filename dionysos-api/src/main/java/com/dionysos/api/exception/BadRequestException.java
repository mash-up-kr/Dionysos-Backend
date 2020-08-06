package com.dionysos.api.exception;

import com.dionysos.api.common.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestException(String message) {
        this(HttpStatus.BAD_REQUEST.value(),
                message
        );
    }

    public BadRequestException(int code,
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
