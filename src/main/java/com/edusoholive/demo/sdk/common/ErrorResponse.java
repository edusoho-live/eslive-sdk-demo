package com.edusoholive.demo.sdk.common;

import lombok.Data;

@Data
public class ErrorResponse {

    private Long timestamp;

    private Long status;

    private String code;

    private String message;

    private String path;

    private String traceId;

    private String details;
}
