package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.suberror.ApiMessageError;
import lombok.Getter;

@Getter
public class OctNotFoundException extends OctException {
    private final ApiMessageError apiMessageError;

    public OctNotFoundException(ErrorMessage errorMessage, ApiMessageError apiMessageError) {
        super(errorMessage);
        this.apiMessageError = apiMessageError;
    }
}
