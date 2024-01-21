package com.ryan.roomreservationservice.util.enums;

import jakarta.persistence.AttributeConverter;

import java.util.HashMap;
import java.util.Map;

public class RoomStatusConverter implements AttributeConverter<RoomStatus, String> {

    private static final Map<String,RoomStatus> statusMap = new HashMap<>();

    static {
        for (RoomStatus status : RoomStatus.values()){
            statusMap.put(status.getStatus(), status);
        }
    }

    @Override
    public String convertToDatabaseColumn(RoomStatus attribute) {
        if (attribute == null) return null;

        return attribute.getStatus();
    }

    @Override
    public RoomStatus convertToEntityAttribute(String data) {
        if (data == null) return null;

        return statusMap.get(data);
    }
}
