package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctInvalidInputException;
import com.octl3.api.commons.suberror.ApiSubError;
import com.octl3.api.commons.suberror.ApiValidatorError;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.FieldName;
import com.octl3.api.constants.MessageConst;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonValidator {
    public void checkDateOfBirth(LocalDate dateOfBirth) {
        checkDateInTheFuture(dateOfBirth, FieldName.DATE_OF_BIRTH);
        LocalDate currentDate = LocalDate.now();
        Period ageDifference = Period.between(dateOfBirth, currentDate);
        int age = ageDifference.getYears();
        if (age <= Const.MIN_AGE) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.DATE_OF_BIRTH, dateOfBirth, MessageConst.AGE_NOT_LESS_THAN_18);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
        if (age >= Const.MAX_AGE) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.DATE_OF_BIRTH, dateOfBirth, MessageConst.AGE_NOT_OVER_60);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
    }

    public void checkPhone(String phone) {
        Pattern pattern = Pattern.compile(Const.PHONE_REGEXP);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.PHONE, phone, MessageConst.PHONE_WRONG_FORMAT);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
    }

    public void checkEmail(String email) {
        Pattern pattern = Pattern.compile(Const.EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.EMAIL, email, MessageConst.EMAIL_WRONG_FORMAT);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
    }

    public void checkCitizenId(String citizenId) {
        Pattern pattern = Pattern.compile(Const.CITIZEN_ID_REGEXP);
        Matcher matcher = pattern.matcher(citizenId);
        if (!matcher.matches()) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.CITIZEN_ID, citizenId, MessageConst.CITIZEN_ID_WRONG_FORMAT);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
    }

    public void checkDateInTheFuture(LocalDate date, String fieldName) {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            ApiSubError apiSubError = new ApiValidatorError(fieldName, date, MessageConst.DATE_NOT_FUTURE);
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, apiSubError);
        }
    }
}
