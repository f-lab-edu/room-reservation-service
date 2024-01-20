package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {
    SUCCESS("success"),
    FAIL("fail"),
    DISASTER("disaster");

    private String statusCode;

    StatusCodeEnum(String statusCode) {
        this.statusCode = statusCode;
    }
}
