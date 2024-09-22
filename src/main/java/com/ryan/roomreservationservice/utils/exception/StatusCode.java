package com.ryan.roomreservationservice.utils.exception;

import lombok.Getter;

@Getter
public enum StatusCode {
    SUCCESS("success"),
    FAIL("fail"),
    DISASTER("disaster");

    private String statusCode;

    StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
