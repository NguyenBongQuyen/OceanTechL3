package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusValidator {
    public void checkValidStatus(String status) {
        if (!Status.isValidStatus(status)) {
            throw new OctException(ErrorMessages.INVALID_STATUS);
        }
    }

    public void checkValidLeaderStatus(String status) {
        if (!Status.isValidLeaderStatus(status)) {
            throw new OctException(ErrorMessages.INVALID_STATUS);
        }
    }
}
