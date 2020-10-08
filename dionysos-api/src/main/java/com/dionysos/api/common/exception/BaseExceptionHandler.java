package com.dionysos.api.common.exception;

import com.dionysos.api.common.dto.ErrorResponseDto;
import com.dionysos.api.common.response.DionysosAPIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.dionysos.api")
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<DionysosAPIErrorResponse<ErrorResponseDto>> handleException(BaseException baseException) {
        ErrorResponseDto errorModel = baseException.getError();

        log.error("BaseException Error : {}",
                errorModel.getMessage()
        );

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        switch (errorModel.getErrorCode()) {
            case FAILURE:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case UNAUTHORIZATION:
                httpStatus = HttpStatus.UNAUTHORIZED;
                break;
            case TOKEN_EXPIRED:
                httpStatus = HttpStatus.UNAUTHORIZED;
                break;
            case EXIST_NICKNAME:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case EXIST_ACCOUNT:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case NOT_EXIST_ACCOUNT:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case NOT_EXIST_DIARY:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case NOT_EXIST_TIMEHISTORY:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                throw new RuntimeException();
        }

        return ResponseEntity.status(httpStatus)
                .body(DionysosAPIErrorResponse.builder()
                        .errorModel(errorModel)
                        .build()
                );
    }

}