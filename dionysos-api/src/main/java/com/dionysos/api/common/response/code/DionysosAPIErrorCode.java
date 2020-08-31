package com.dionysos.api.common.response.code;

import lombok.Getter;

@Getter
public enum DionysosAPIErrorCode {

    FAILURE(1000, "응답에 실패하였습니다."),

    NOT_EXIST_ACCOUNT(1100, "존재하지 않는 계정입니다."),
    EXIST_ACCOUNT(1101, "이미 있는 계정입니다."),
    EXIST_NICKNAME(1102, "닉네임이 중복됩니다."),

    UNAUTHORIZATION(1500, "JWT 인증에 실패하였습니다."),
    TOKEN_EXPIRED(1501, "토큰 기간이 만료됐습니다."),

    NOT_EXIST_DIARY(2000, "존재하지 않는 일기입니다.");


    private int code;
    private String message;

    private DionysosAPIErrorCode(int code,
                                 String message
    ) {
        this.code = code;
        this.message = message;
    }

}
