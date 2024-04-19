package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class OctExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<String>> handleException(Exception exception) {
        log.info("handleOctException. Msg = {}", exception.getMessage(), exception);
        return new ResponseEntity<>(new DataResponse<>(ErrorMessages.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<DataResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.info("handleMethodArgumentNotValid. Msg = {}", ex.getMessage(), ex);
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    details.put(fieldName, message);
                });
        return new ResponseEntity<>(DataResponse.build(details, ErrorMessages.INVALID_VALUE), HttpStatus.OK);
    }

    @ExceptionHandler(OctException.class)
    protected ResponseEntity<DataResponse<String>> handleOctException(OctException ex) {
        log.info("handleOctException. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return new ResponseEntity<>(new DataResponse<>(ex.getErrMsg()), HttpStatus.OK);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DataResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
        log.info("handleAccessDeniedException. Msg = {}", ex.getMessage(), ex);
        return new ResponseEntity<>(DataResponse.build(ex.getMessage(), ErrorMessages.FORBIDDEN), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.info("handleHttpMessageNotReadableException. Msg = {}", ex.getMessage(), ex);
        return new ResponseEntity<>(DataResponse.build(ex.getMessage(), ErrorMessages.INVALID_DATE), HttpStatus.BAD_REQUEST);
    }

}
