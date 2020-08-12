package com.dionysos.api.common.response.code;

import lombok.Getter;

@Getter
public enum DionysosAPIErrorCode {

    FAILURE(1000, "응답에 실패하였습니다."),

    NOT_EXIST_ACCOUNT(1100, "존재하지 않는 계정입니다."),
    EXIST_NICKNAME(1101, "닉네임이 중복됩니다."),

    UNAUTHORIZATION(2000, "JWT 인증에 실패하였습니다."),
    TOKEN_EXPIRED(2001, "토큰 기간이 만료됐습니다.");


    private int code;
    private String description;

    private DionysosAPIErrorCode(int code,
                                 String description
    ) {
        this.code = code;
        this.description = description;
    }

}
