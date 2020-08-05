package com.dionysos.api.common.utils;

import lombok.Getter;

@Getter
public enum DionysosAPIResponseCode {

    A(100, ""),
    B(101, ""),
    C(102, "");

    private int code;
    private String description;

    DionysosAPIResponseCode(int code,
                            String description
    ) {
        this.code = code;
        this.description = description;
    }

}
