package com.octl3.api.validator;

import com.octl3.api.validator.anotations.Username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.octl3.api.constants.Const.USERNAME_REGEX;
import static com.octl3.api.constants.Const.USERNAME_REGEX;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username != null && username.matches(USERNAME_REGEX);
    }
}
