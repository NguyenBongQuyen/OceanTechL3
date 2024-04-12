package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.octl3.api.constants.SecurityConst.NOT_ALLOW;
import static com.octl3.api.constants.SecurityConst.UN_AUTHENTICATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public DataResponse<UserResponseDto> register(@Valid @RequestBody UserDto userDto) {
        return DataResponse.ok(userService.saveUser(userDto));
    }

    @PostMapping("/login")
    public DataResponse<TokenResponse> login(@RequestBody UserLogin userLogin) {
        return DataResponse.ok(userService.login(userLogin));
    }
    
    @GetMapping("/not-allow")
    public DataResponse<String> notAllow() {
        return DataResponse.build(NOT_ALLOW, ErrorMessages.FORBIDDEN);
    }

    @GetMapping("/un-authentication")
    public DataResponse<String> unAuthentication() {
        return DataResponse.build(UN_AUTHENTICATION, ErrorMessages.UNAUTHORIZED);
    }
}
