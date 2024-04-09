package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.security.CustomUserDetails;
import com.octl3.api.security.JwtTokenProvider;
import com.octl3.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {


    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/register")
    public DataResponse<UserResponseDto> register(@Valid @RequestBody UserDto userDto) {
        return DataResponse.ok(userService.saveUser(userDto));
    }

    @PostMapping("/login")
    public DataResponse<TokenResponse> login(@Valid @RequestBody UserLogin userLogin) {
        return DataResponse.ok(userService.login(userLogin));
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/leader")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String leader() {
        return "admin";
    }

    @GetMapping("/manager")
//    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String manager() {
        return "manager";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
}
