package com.bustracker.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    private int code;
    private LocalDateTime timestamp;
    private String msg;
    private String details;

}