package com.dionysos.api.common.utils;

import lombok.Getter;

@Getter
public enum DionysosAPIErrorCode {

    A(100, ""),
    B(101, ""),
    C(102, "");

    private int code;
    private String description;

    private DionysosAPIErrorCode(int code,
                                 String description
    ) {
        this.code = code;
        this.description = description;
    }

}
