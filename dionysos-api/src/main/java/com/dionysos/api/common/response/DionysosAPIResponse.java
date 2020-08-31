package com.dionysos.api.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DionysosAPIResponse<T> {

    private T result;

    @Builder
    private DionysosAPIResponse(T result) {
        this.result = result;
    }

}
