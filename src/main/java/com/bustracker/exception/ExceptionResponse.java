package com.bustracker.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionResponse {
    private int code;
    private Date timestamp;
    private String msg;
    private String details;

}