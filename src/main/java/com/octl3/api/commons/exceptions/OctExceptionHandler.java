package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class OctExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<String>> handleException(Exception exception) {
        log.info("handleOctException. Msg = {}", exception.getMessage(), exception);
        return new ResponseEntity<>(new DataResponse<>(ErrorMessages.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<DataResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.info("handleMethodArgumentNotValid");
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

}
