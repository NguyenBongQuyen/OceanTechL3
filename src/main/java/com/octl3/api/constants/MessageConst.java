package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConst {
    public static final String SUBMIT_SUCCESS = "Submit success!";
    public static final String DELETE_SUCCESS = "Delete success!";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number";
    public static final String INVALID_PASSWORD_FORMAT = "Invalid password format";
    public static final String INVALID_USERNAME_FORMAT = "Invalid username format";
    public static final String AGE_NOT_LESS_THAN_18 = "Age not less than 18";
    public static final String AGE_NOT_OVER_60 = "Age not over 60";
    public static final String PHONE_WRONG_FORMAT = "The phone number must have 10 or 11 numbers";
    public static final String EMAIL_WRONG_FORMAT = "Email wrong format";
    public static final String CITIZEN_ID_WRONG_FORMAT = "Citizen id must have 12 numbers";
    public static final String DATE_NOT_FUTURE = "Date can not be in the future";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Employee {
        public static final String NOT_FOUND = "Employee not found";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Certificate {
        public static final String NOT_FOUND = "Certificate not found";

    }

}
