package com.dionysos.api.common;

import com.dionysos.api.common.utils.DionysosAPIResponseCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DionysosAPIResponse<T> {

    private int code;
    private String message;
    private T data;

    @Builder
    private DionysosAPIResponse(DionysosAPIResponseCode responseCode,
                                String message,
                                T data
    ) {
        this.code = responseCode.getCode();
        this.message = message;
        this.data = data;
    }

}
