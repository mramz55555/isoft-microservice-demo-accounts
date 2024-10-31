package com.isoft.accounts.exception;

import com.isoft.accounts.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> globalExceptionsHandler(Exception e, WebRequest webRequest) {
        return this.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), webRequest, e);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponseDTO> entityExistenceAndIllegalArgExceptionsHandler(EntityAlreadyExistsException e, WebRequest webRequest) {
        return this.getResponseEntity(HttpStatus.BAD_REQUEST.value(), webRequest, e);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> entityNotFoundExceptionHandler(EntityNotFoundException e, WebRequest webRequest) {
        return this.getResponseEntity(HttpStatus.NOT_FOUND.value(), webRequest, e);
    }

    private ResponseEntity<ErrorResponseDTO> getResponseEntity(int statusCode, WebRequest webRequest, Exception e) {
        return ResponseEntity.status(statusCode).body(new ErrorResponseDTO(webRequest.getDescription(false), statusCode, e.getMessage(), LocalDateTime.now()));
    }
}
