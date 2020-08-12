package com.dionysos.api.common.response.code;

import lombok.Getter;

@Getter
public enum DionysosAPIResponseCode {

    SUCCESS(100, "응답에 정상적으로 성공했습니다."),

    NOT_EXIST_NICKNAME(200, "닉네임이 중복되지 않습니다.");

    private int code;
    private String description;

    DionysosAPIResponseCode(int code,
                            String description
    ) {
        this.code = code;
        this.description = description;
    }

}
