package com.ryan.roomreservationservice.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse<T> {
    private Common common;
    @Schema(description = "클라이언트의 요청 된 결과 데이터르 반환")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static SuccessResponse<Void> setDefaultResponse() {
        return SuccessResponse.<Void>builder()
                .common(Common.builder().build())
                .build();
    }

    public static <T> SuccessResponse<T> setSuccessResponse(T data) {
        return SuccessResponse.<T>builder()
                .common(Common.builder().build())
                .data(data)
                .build();
    }
}

