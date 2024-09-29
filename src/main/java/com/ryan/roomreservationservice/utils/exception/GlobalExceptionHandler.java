package com.ryan.roomreservationservice.utils.exception;

import com.ryan.roomreservationservice.utils.response.Common;
import com.ryan.roomreservationservice.utils.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGlobalException(
            Exception exception,
            HttpServletRequest httpServletRequest
    ) {
        log.error("Exception [Path]: {} [Message]: {}", httpServletRequest.getRequestURI(), exception.getMessage());
        return ErrorResponse.builder()
                .common(Common.builder()
                        .status(StatusCode.DISASTER.getStatusCode())
                        .message(ErrorMessage.SERVER_ERROR)
                        .build())
                .build();
    }

    @ExceptionHandler(CommonException.class)
    public ErrorResponse handleCommonException(
            CommonException commonException,
            HttpServletRequest httpServletRequest
    ) {
        log.debug("CommonException [Path]: {} [ClientErrorMessage]: {} [ServerErrorMessage]: {}",
                httpServletRequest.getRequestURI(),
                commonException.getClientErrorMessage(),
                commonException.getServerErrorMessage()
        );
        return ErrorResponse.builder()
                .common(Common.builder()
                        .status(StatusCode.FAIL.getStatusCode())
                        .message(commonException.getClientErrorMessage())
                        .build())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(
            MethodArgumentNotValidException exception,
            HttpServletRequest httpServletRequest
    ) {
        List<String> errorMessages = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    } else {
                        return error.getDefaultMessage();
                    }
                })
                .collect(Collectors.toList());

        log.error("MethodArgumentNotValidException [Path]: {} [Message]: {}", httpServletRequest.getRequestURI(), errorMessages);

        return ErrorResponse.builder()
                .common(Common.builder()
                        .status(StatusCode.FAIL.getStatusCode())
                        .message(errorMessages)
                        .build())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(
            IllegalArgumentException illegalArgumentException,
            HttpServletRequest httpServletRequest
    ) {
        log.debug("IllegalArgumentException [Path]: {} [Message]: {}",
                httpServletRequest.getRequestURI(),
                illegalArgumentException.getMessage()
        );
        return ErrorResponse.builder()
                .common(Common.builder()
                        .status(StatusCode.FAIL.getStatusCode())
                        .message(illegalArgumentException.getMessage())
                        .build())
                .build();
    }
}
