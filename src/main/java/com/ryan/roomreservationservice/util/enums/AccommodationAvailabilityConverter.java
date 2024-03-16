package com.ryan.roomreservationservice.util.enums;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class AccommodationAvailabilityConverter implements AttributeConverter<AccommodationAvailability, String> {

    @Override
    public String convertToDatabaseColumn(AccommodationAvailability attribute) {
        if (attribute == null) return null;

        return attribute.getStatus();
    }

    @Override
    public AccommodationAvailability convertToEntityAttribute(String data) {
        if (data == null) return null;

        return Arrays.stream(AccommodationAvailability.values())
                .filter(type -> type.getStatus().equals(data))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}