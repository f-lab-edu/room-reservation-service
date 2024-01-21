package com.ryan.roomreservationservice.util.enums;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ReservationStatus attribute) {
        if (attribute == null) return null;

        return attribute.getStatus();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String data) {
        if (data == null) return null;

        return Arrays.stream(ReservationStatus.values())
                .filter(type -> type.getStatus().equals(data))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}