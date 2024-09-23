package com.ryan.roomreservationservice.utils.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Common common;
}
