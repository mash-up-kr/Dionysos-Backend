package com.dionysos.api.timehistory.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;

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
