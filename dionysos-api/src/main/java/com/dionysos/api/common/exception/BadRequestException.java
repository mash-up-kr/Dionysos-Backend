package com.dionysos.api.common.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(DionysosAPIErrorCode.FAILURE);
    }

    public BadRequestException(DionysosAPIErrorCode errorCode) {
        super(ErrorResponseDto.builder()
                .errorCode(errorCode)
                .build()
        );
    }

}
