package com.dionysos.api.user.exception;

import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotExistNicknameException extends BaseException {
    public NotExistNicknameException() {
        this("이미 존재하는 닉네임입니다.");
    }

    public NotExistNicknameException(String message) {
        this(HttpStatus.FOUND.value(),
                message
        );
    }

    public NotExistNicknameException(int code,
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
