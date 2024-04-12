package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Const {
    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 60;
    public static final String PHONE_REGEXP = "^[0-9]{10,11}$";
    public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}";
    public static final String CITIZEN_ID_REGEXP = "^[0-9]{12}$";
    public static final int EXISTS_EMPLOYEE = 1;
    public static final int EXISTS_CERTIFICATE = 1;
    public static final int EXISTS_RELATIONSHIP = 1;
    public static final int EXISTS_PROFILE_END = 1;

}
