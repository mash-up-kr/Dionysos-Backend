package com.dionysos.api.user.exception;

import com.dionysos.api.common.model.ErrorModel;
import com.dionysos.api.common.response.DionysosAPIErrorResponse;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "com.dionysos.api.user")
public class UserExceptionHandler {

    @ExceptionHandler(UserBaseException.class)
    public ResponseEntity<DionysosAPIErrorResponse<ErrorModel>> handleUserException(UserBaseException baseException) {
        ErrorModel errorModel = baseException.getError();

        log.error("User Rest API Error : {}",
                errorModel.getMessage()
        );

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        DionysosAPIErrorCode resultCode = DionysosAPIErrorCode.FAILURE;

        List<ErrorModel> errors = new ArrayList<>();

        switch (errorModel.getErrorCode()) {
            case FAILURE:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case UNAUTHORIZATION:
                httpStatus = HttpStatus.UNAUTHORIZED;
                errors.add(ErrorModel.builder()
                        .errorCode(DionysosAPIErrorCode.UNAUTHORIZATION)
                        .timestamp(baseException.getError().getTimestamp())
                        .build()
                );
                break;
            case EXIST_NICKNAME:
                httpStatus = HttpStatus.FOUND;
                break;
            default:
                throw new RuntimeException();
        }

        return ResponseEntity.status(httpStatus)
                .body(DionysosAPIErrorResponse.builder()
                        .errorCode(resultCode)
                        .errors(errors)
                        .build()
                );
    }

}