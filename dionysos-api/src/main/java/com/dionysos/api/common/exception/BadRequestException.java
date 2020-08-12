package com.dionysos.api.common.exception;

import com.dionysos.api.common.model.ErrorModel;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(DionysosAPIErrorCode.FAILURE);
    }

    public BadRequestException(DionysosAPIErrorCode errorCode) {
        super(ErrorModel.builder()
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }

}
