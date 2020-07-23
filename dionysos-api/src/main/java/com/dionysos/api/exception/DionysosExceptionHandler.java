package com.dionysos.api.exception;

import com.dionysos.api.exception.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DionysosExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorModel> handleException(BaseException baseException) {
        ErrorModel errorModel = baseException.error;
        log.error("Rest API Error : {}", errorModel.getMsg());

        switch (errorModel.getCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
            case 401:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorModel);
            default:
                throw new RuntimeException();
        }
    }

}
