package com.ryan.roomreservationservice.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryan.roomreservationservice.utils.exception.StatusCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Common {
    @Schema(description = "서버에서 클라이언트에게 HTTP 요청의 대한 응답 시간을 정한다.", example = "2024-02-16T21:17:26.516190Z")
    @Builder.Default
    private String createdAt = Instant.now().toString();

    @Schema(description = "응답 상태(success: 성공, fail: 실패, disaster: 매우 실패)", example = "success")
    @Builder.Default
    private String status = StatusCode.SUCCESS.getStatusCode();

    @Schema(description = "status가 success 아닐때, 에러에 대한 설명이 포함된다. 이 내용은 사용자나 조작자에게 노출되어도 무방한 내용을 포함한다.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object message;
}
