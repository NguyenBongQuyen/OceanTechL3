package com.octl3.api.validator;

import com.octl3.api.validator.anotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.octl3.api.constants.Const.PASSWORD_REGEXP;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username != null && username.matches(PASSWORD_REGEXP);
    }
}
