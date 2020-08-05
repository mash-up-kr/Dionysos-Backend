package com.dionysos.api.common.response.code;

import lombok.Getter;

@Getter
public enum DionysosAPIResponseCode {

    SUCCESS(100, "응답에 성공했을 때 반환"),
    SUCCESS_LOGIN(200, "로그인에 성공했을 때 반환");

    private int code;
    private String description;

    DionysosAPIResponseCode(int code,
                            String description
    ) {
        this.code = code;
        this.description = description;
    }

}
