package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConst {

    public static final String HEADER = "Authorization";
    public static final String TOKEN_TYPE = "Bearer";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String LEADER = "LEADER";
    public static final String MANAGER = "MANAGER";
    public static final String API_FORBIDDEN = "/api/v1/auth/un-authorization";
    public static final String API_UN_AUTHENTICATION = "/api/v1/auth/un-authentication";

}
