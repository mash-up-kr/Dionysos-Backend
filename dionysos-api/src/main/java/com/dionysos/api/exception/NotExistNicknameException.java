package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotExistNicknameException extends BaseException {
    public NotExistNicknameException() {
        this("이미 존재하는 닉네임입니다.");
    }

    public NotExistNicknameException(String msg) {
        this(HttpStatus.FOUND.value(), msg);
    }

    public NotExistNicknameException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
