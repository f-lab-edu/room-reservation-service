package com.ryan.roomreservationservice.util.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Common common;
}
