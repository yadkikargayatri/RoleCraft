package com.rolecraft.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rolecraft.controller.dto.ApiError;
import com.rolecraft.controller.dto.ApiResponse;
import com.rolecraft.exception.InvalidJobDescriptionException;
import com.rolecraft.exception.InvalidResumeException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidResumeException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidResume(
            InvalidResumeException ex) {

        ApiError error = new ApiError(
                "INVALID_RESUME",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(error));
    }

    @ExceptionHandler(InvalidJobDescriptionException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidJobDescription(
            InvalidJobDescriptionException ex) {

        ApiError error = new ApiError(
                "INVALID_JOB_DESCRIPTION",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(error));
    }

    // DTO Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getDefaultMessage())
                .orElse("Validation failed");

        ApiError error = new ApiError(
                "VALIDATION_ERROR",
                message,
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(error));
    }

    // Catch-all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {

        ApiError error = new ApiError(
                "INTERNAL_SERVER_ERROR",
                "Unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(error));
    }
}
