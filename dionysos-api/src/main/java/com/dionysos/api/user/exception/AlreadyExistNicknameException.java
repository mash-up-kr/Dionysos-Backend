package com.dionysos.api.user.exception;

import com.dionysos.api.common.model.ErrorModel;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyExistNicknameException extends UserBaseException {

    public AlreadyExistNicknameException() {
        this(DionysosAPIErrorCode.EXIST_NICKNAME);
    }

    public AlreadyExistNicknameException(DionysosAPIErrorCode errorCode) {
        super(ErrorModel.builder()
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }
}
