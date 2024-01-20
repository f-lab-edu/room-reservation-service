package com.ryan.roomreservationservice.util.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse<T>{
    private Common common;
    private T data;

    public static <T> SuccessResponse<T> setSuccessResponse(T data){
        return SuccessResponse.<T>builder()
                .common(Common.builder().build())
                .data(data)
                .build();
    }
}

