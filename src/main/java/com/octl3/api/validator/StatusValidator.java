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

    public void checkValidStatusForManagerUpdate(String status) {
        if (!Status.isValidStatusForManagerUpdate(status)) {
            throw new OctException(ErrorMessages.NOT_ALLOW_UPDATE);
        }
    }

    public void checkValidStatusForSubmit(String status) {
        if (!Status.isValidStatusForSubmit(status)) {
            throw new OctException(ErrorMessages.NOT_ALLOW_UPDATE);
        }
    }

    public void checkValidStatusForLeaderUpdate(String status) {
        if (!Status.isValidStatusForLeaderUpdate(status)) {
            throw new OctException(ErrorMessages.NOT_ALLOW_UPDATE);
        }
    }

}
