package com.rolecraft.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rolecraft.controller.dto.ApiError;
import com.rolecraft.controller.dto.ApiResponseWrapper;
import com.rolecraft.exception.InvalidJobDescriptionException;
import com.rolecraft.exception.InvalidResumeException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidResumeException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleInvalidResume(
            InvalidResumeException ex) {

        ApiError error = new ApiError(
                "INVALID_RESUME",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseWrapper.failure(error));
    }

    @ExceptionHandler(InvalidJobDescriptionException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleInvalidJobDescription(
            InvalidJobDescriptionException ex) {

        ApiError error = new ApiError(
                "INVALID_JOB_DESCRIPTION",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseWrapper.failure(error));
    }

    // DTO Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleValidation(
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
                .body(ApiResponseWrapper.failure(error));
    }

    // Catch-all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleGeneric(Exception ex) {

   ex.printStackTrace(); // Log the exception for debugging (temporary)

        ApiError error = new ApiError(
                "INTERNAL_SERVER_ERROR",
                "Unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseWrapper.failure(error));
    }
}
