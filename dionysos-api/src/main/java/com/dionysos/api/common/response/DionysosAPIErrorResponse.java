package com.dionysos.api.common.response;

import com.dionysos.api.common.model.ErrorModel;
import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DionysosAPIErrorResponse<T extends ErrorModel> {

    private int code;
    private String message;

    @Builder
    private DionysosAPIErrorResponse(DionysosAPIErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getDescription();
    }

}
