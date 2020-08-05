package com.dionysos.api.user.exception;

import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotExistUserException extends BaseException {

    public NotExistUserException() {
        this("존재하지 않는 ID 입니다.");
    }

    public NotExistUserException(String message) {
        this(HttpStatus.NOT_FOUND.value(),
                message
        );
    }

    public NotExistUserException(int code,
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
