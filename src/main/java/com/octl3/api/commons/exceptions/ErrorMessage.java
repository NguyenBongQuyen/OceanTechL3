package com.octl3.api.commons.exceptions;

import java.io.Serializable;

public interface ErrorMessage extends Serializable {
    int getCode();
    String getMessage();
}
