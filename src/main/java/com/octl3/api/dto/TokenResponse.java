package com.octl3.api.dto;

import lombok.Getter;
import lombok.Setter;

import static com.octl3.api.constants.SecurityConst.TOKEN_TYPE;

@Getter
@Setter
public class TokenResponse {

    private String accessToken;
    private String tokenType;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = TOKEN_TYPE;
    }
}
