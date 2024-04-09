package com.octl3.api.service.impl;

import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.security.CustomUserDetails;
import com.octl3.api.security.JwtTokenProvider;
import com.octl3.api.service.RoleService;
import com.octl3.api.service.UserService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;
import java.util.Objects;

import static com.octl3.api.constants.StoredProcedure.Mapper.USER_RESPONSE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.USER_JSON;
import static com.octl3.api.constants.StoredProcedure.User.CREATE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;


    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (ObjectUtils.isEmpty(userDto.getRoleId()) || !roleService.isExistRoleById(userDto.getRoleId())) {
            userDto.setRoleId(1); // set default role
        }

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_USER, USER_RESPONSE_DTO_MAPPER)
                .registerStoredProcedureParameter(USER_JSON, String.class, ParameterMode.IN)
                .setParameter(USER_JSON, JsonUtil.objectToJson(userDto));
        return (UserResponseDto) query.getSingleResult();
    }

    public TokenResponse login(UserLogin userLogin) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new TokenResponse(jwt);
    }
}
