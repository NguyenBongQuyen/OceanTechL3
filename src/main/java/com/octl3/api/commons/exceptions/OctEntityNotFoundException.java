package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.suberror.ApiMessageError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OctEntityNotFoundException extends OctException {
    private final ApiMessageError apiMessageError;

    public OctEntityNotFoundException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
