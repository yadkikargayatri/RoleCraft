package com.rolecraft.controller.advice;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidResumeException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleInvalidResume(
            InvalidResumeException ex) {

        return buildErrorResponse(
                "INVALID_RESUME",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidJobDescriptionException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleInvalidJobDescription(
            InvalidJobDescriptionException ex) {

        return buildErrorResponse(
                "INVALID_JOB_DESCRIPTION",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // DTO Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildErrorResponse(
                "VALIDATION_ERROR",
                message,
                HttpStatus.BAD_REQUEST
        );
    }

    // Catch-all fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleGeneric(Exception ex) {

        logger.error("Unhandled exception occurred", ex);

        return buildErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "Unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ResponseEntity<ApiResponseWrapper<Object>> buildErrorResponse(
            String code,
            String message,
            HttpStatus status) {

        ApiError error = new ApiError(
                code,
                message,
                status.value()
        );

        return ResponseEntity
                .status(status)
                .body(ApiResponseWrapper.failure(error));
    }
}