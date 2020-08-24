package com.dionysos.api.diary.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;

public class NotFoundDiaryException extends BaseException {
    public NotFoundDiaryException() {
        this(DionysosAPIErrorCode.NOT_EXIST_DIARY);
    }

    public NotFoundDiaryException(DionysosAPIErrorCode errorCode) {
        super(ErrorResponseDto.builder()
                .errorCode(errorCode)
                .build()
        );
    }
}
