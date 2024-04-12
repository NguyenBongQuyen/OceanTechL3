package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Const {
    public static final String PHONE_REGEX = "\\d{10,11}";
    public static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_-]{2,50}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,20}$";


}
