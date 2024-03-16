package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

@Getter
public enum CommonStatusCode {
    SUCCESS("success"),
    FAIL("fail"),
    DISASTER("disaster");

    private String statusCode;

    CommonStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
