package com.dionysos.api.timehistory.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotFoundTimeHistoryException extends BaseException {
    public NotFoundTimeHistoryException() {
        this(DionysosAPIErrorCode.NOT_EXIST_TIMEHISTORY);
    }

    public NotFoundTimeHistoryException(DionysosAPIErrorCode errorCode) {
        super(ErrorResponseDto.builder()
                .errorCode(errorCode)
                .build()
        );
    }
}
