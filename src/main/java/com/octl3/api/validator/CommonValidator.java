package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class CommonValidator {
    public void checkDateOfBirth(LocalDate dateOfBirth) {
        checkDateInTheFuture(dateOfBirth);
        LocalDate currentDate = LocalDate.now();
        Period ageDifference = Period.between(dateOfBirth, currentDate);
        int age = ageDifference.getYears();
        if (age <= Const.MIN_AGE || age >= Const.MAX_AGE) {
            throw new OctException(ErrorMessages.INVALID_DATE_OF_BIRTH);
        }
    }

    public void checkDateInTheFuture(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            throw new OctException(ErrorMessages.DATE_CAN_NOT_IN_THE_FUTURE);
        }
    }

}
