package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotExistUserException extends BaseException {

    public NotExistUserException() {
        this("존재하지 않는 ID 입니다.");
    }

    public NotExistUserException(String msg) {
        this(HttpStatus.NOT_FOUND.value(), msg);
    }

    public NotExistUserException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }

}
