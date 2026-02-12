package com.rolecraft.controller.dto;

public class ApiResponseWrapper<T> {

    private final boolean success;
    private final T data;
    private final ApiError error;

    private ApiResponseWrapper(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponseWrapper<T> success(T data) {
        return new ApiResponseWrapper<>(true, data, null);
    }

    public static <T> ApiResponseWrapper<T> failure(ApiError error) {
        return new ApiResponseWrapper<>(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
