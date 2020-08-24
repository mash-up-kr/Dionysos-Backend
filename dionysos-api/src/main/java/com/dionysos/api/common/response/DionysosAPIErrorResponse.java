package com.dionysos.api.common.response;

import com.dionysos.api.common.dto.ErrorResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DionysosAPIErrorResponse<E extends ErrorResponseDto> {

    private E error;

    @Builder
    private DionysosAPIErrorResponse(E errorModel) {
        this.error = errorModel;
    }

}
