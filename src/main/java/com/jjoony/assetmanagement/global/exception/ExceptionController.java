package com.jjoony.assetmanagement.global.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validException(MethodArgumentNotValidException e){

        Map<String, String> validation = new HashMap<>();

        for (FieldError error : e.getFieldErrors()) {
            validation.put(error.getField(), error.getDefaultMessage());
        } //TODO -- 추후에 메시지는 프로퍼티에서 관리

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("잘못된 요청입니다.")
                .validation(validation)
                .build();

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> exception(ApiException e){

        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(e.getErrorCode().getHttpStatus())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> defaultException(Exception e){

        log.error("Exception : ", e);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("관리자에게 문의해주세요.")
                .build();

        return ResponseEntity
                .internalServerError()
                .body(errorResponse);
    }
}
