package com.octl3.api.commons;

import com.octl3.api.commons.exceptions.ErrorMessage;
import com.octl3.api.commons.exceptions.ErrorMessages;
import lombok.Getter;

@Getter
public class DataResponse<T> {
    private T data;
    private final int code;
    private final String message;

    public DataResponse(T data, ErrorMessage message) {
        this.data = data;
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public DataResponse(ErrorMessage message) {
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public DataResponse(T data) {
        this.data = data;
        this.code = ErrorMessages.SUCCESS.getCode();
        this.message = ErrorMessages.SUCCESS.getMessage();
    }

    public static <T> DataResponse<T> build(T data, ErrorMessage messages) {
        return new DataResponse<>(data, messages);
    }

    public static <T> DataResponse<T> ok(T data) {
        return new DataResponse<>(data);
    }
}
