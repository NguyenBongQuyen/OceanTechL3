package com.octl3.api.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLogin {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
