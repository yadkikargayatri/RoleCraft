package com.rolecraft.controller.dto;

public class ApiError {

    private final String code;
    private final String message;
    private final int status;

    public ApiError(String code, String message, int status) {
         this.code = code;
         this.message = message;
         this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
