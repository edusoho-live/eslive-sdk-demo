package com.edusoholive.demo.sdk;

public class EsliveApiException extends RuntimeException {

    private String code = "";

    public EsliveApiException(String code, String message) {
        super(message);
        this.code = code;
    }
}
