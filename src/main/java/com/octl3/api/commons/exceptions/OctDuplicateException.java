package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.suberror.ApiMessageError;
import lombok.Getter;

@Getter
public class OctDuplicateException extends OctException {
    private final ApiMessageError apiMessageError;

    public OctDuplicateException(ErrorMessage errorMessage, ApiMessageError apiMessageError) {
        super(errorMessage);
        this.apiMessageError = apiMessageError;
    }
}
