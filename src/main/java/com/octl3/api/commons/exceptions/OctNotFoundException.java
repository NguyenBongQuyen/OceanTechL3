package com.octl3.api.commons.exceptions;

import com.octl3.api.commons.suberror.ApiSubError;
import lombok.Getter;

@Getter
public class OctNotFoundException extends OctException {
    private final ApiSubError apiSubError;

    public OctNotFoundException(ErrorMessage errorMessage, ApiSubError apiSubError) {
        super(errorMessage);
        this.apiSubError = apiSubError;
    }
}
