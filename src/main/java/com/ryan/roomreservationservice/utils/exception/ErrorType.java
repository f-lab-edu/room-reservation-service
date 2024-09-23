package com.ryan.roomreservationservice.utils.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    SYSTEM("SYSTEM"),
    DEVELOPER("DEVELOPER");

    private String errorType;

    ErrorType(String errorType) {
        this.errorType = errorType;
    }
}
