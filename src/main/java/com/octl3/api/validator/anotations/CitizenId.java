package com.octl3.api.validator.anotations;

import com.octl3.api.constants.MessageConst;
import com.octl3.api.validator.CitizenIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CitizenIdValidator.class)
public @interface CitizenId {
    String message() default MessageConst.CITIZEN_ID_WRONG_FORMAT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
