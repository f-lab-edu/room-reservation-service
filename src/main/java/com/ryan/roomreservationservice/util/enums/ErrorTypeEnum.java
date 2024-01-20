package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {
    SYSTEM("SYSTEM"),
    DEVELOPER("DEVELOPER");

    private String errorType;

    ErrorTypeEnum(String errorType) {
        this.errorType = errorType;
    }
}
