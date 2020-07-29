package com.dionysos.api.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DionysosAPIResponse<T> {

    private int code;
    private String message;
    private T data;

    @Builder
    private DionysosAPIResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
