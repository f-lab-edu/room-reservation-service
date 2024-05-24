package com.ryan.roomreservationservice.domain.enums;

public enum Status {
    ACTIVE("활성"),
    INACTIVE("비활성");
    private final String status;

    Status(String status) {
        this.status = status;
    }
}
