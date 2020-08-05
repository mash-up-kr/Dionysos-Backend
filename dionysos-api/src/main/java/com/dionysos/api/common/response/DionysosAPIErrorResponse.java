package com.dionysos.api.common.response;

import com.dionysos.api.common.model.ErrorModel;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DionysosAPIErrorResponse<T extends ErrorModel> {

    private int code;
    private String message;
    private List<T> errors;

    @Builder
    private DionysosAPIErrorResponse(DionysosAPIErrorCode errorCode,
                                     String message,
                                     List<T> errors
    ) {
        this.code = errorCode.getCode();
        this.message = message;
        this.errors = errors;
    }

}
