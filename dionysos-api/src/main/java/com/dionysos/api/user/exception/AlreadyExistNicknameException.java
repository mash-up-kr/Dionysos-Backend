package com.dionysos.api.user.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyExistNicknameException extends BaseException {

    public AlreadyExistNicknameException() {
        this(DionysosAPIErrorCode.EXIST_NICKNAME);
    }

    public AlreadyExistNicknameException(DionysosAPIErrorCode errorCode) {
        super(ErrorResponseDto.builder()
                .errorCode(errorCode)
                .build()
        );
    }
}
