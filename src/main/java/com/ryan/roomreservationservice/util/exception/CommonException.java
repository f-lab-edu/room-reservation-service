package com.ryan.roomreservationservice.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonException extends RuntimeException {
    @Builder.Default
    private ErrorType errorType = ErrorType.SYSTEM;
    @Builder.Default
    private String status =  CommonStatusCode.DISASTER.getStatusCode();
    private String clientErrorMessage;
    private String serverErrorMessage;
}
