package com.octl3.api.validator;

import com.octl3.api.validator.anotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.octl3.api.constants.Const.PHONE_REGEXP;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phone != null && phone.matches(PHONE_REGEXP);
    }
}
