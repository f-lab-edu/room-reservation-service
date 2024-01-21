package com.ryan.roomreservationservice.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryan.roomreservationservice.util.enums.ErrorTypeEnum;
import com.ryan.roomreservationservice.util.enums.StatusCodeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonException extends RuntimeException {
    @Builder.Default
    private ErrorTypeEnum errorTypeEnum  = ErrorTypeEnum.SYSTEM;
    @Builder.Default
    private String status =  StatusCodeEnum.DISASTER.getStatusCode();
    private String clientErrorMessage;
    private String serverErrorMessage;
}
