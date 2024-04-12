package com.octl3.api.validator;

import com.octl3.api.constants.Const;
import com.octl3.api.validator.anotations.CitizenId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CitizenIdValidator implements ConstraintValidator<CitizenId, String> {
    @Override
    public boolean isValid(String citizenId, ConstraintValidatorContext constraintValidatorContext) {
        return citizenId != null && citizenId.matches(Const.CITIZEN_ID_REGEXP);
    }
}
