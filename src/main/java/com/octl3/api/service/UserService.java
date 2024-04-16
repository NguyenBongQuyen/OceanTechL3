package com.octl3.api.service;

import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto saveUser(UserDto userDto);

    TokenResponse login(UserLogin userLogin);

    List<UserResponseDto> getAllLeader();

    UserResponseDto getLeaderById(Long id);
    
}
