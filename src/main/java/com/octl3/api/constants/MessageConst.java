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
    public static final String UN_AUTHORIZATION = "You do not have access to this resource!";
    public static final String UN_AUTHENTICATION  = "Authentication information is incorrect, please log in to continue!";
    public static final String CITIZEN_ID_WRONG_FORMAT = "Citizen id must have 12 numbers";
}
