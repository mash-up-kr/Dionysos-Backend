package com.dionysos.api.common.utils;

import lombok.Getter;

@Getter
public enum DionysosAPIErrorCode {

    FAILURE(101, "응답에 실패했을 때 반환"),
    FAILURE_LOGIN(201, "로그인에 실패했을 때 반환");

    private int code;
    private String description;

    private DionysosAPIErrorCode(int code,
                                 String description
    ) {
        this.code = code;
        this.description = description;
    }

}
