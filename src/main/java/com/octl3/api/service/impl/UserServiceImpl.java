package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.security.CustomUserDetails;
import com.octl3.api.security.JwtTokenProvider;
import com.octl3.api.service.RoleService;
import com.octl3.api.service.UserService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import java.util.List;

import static com.octl3.api.constants.SecurityConst.MANAGER;
import static com.octl3.api.constants.StoredProcedure.Mapper.USER_RESPONSE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.User.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserValidator userValidator;


    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        userValidator.checkUserRegister(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (ObjectUtils.isEmpty(userDto.getRoleId()) || !roleService.isExistRoleById(userDto.getRoleId())) {
            userDto.setRoleId(roleService.getRoleByName(MANAGER).getId()); // set default role
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_USER, USER_RESPONSE_DTO_MAPPER)
                .registerStoredProcedureParameter(USER_JSON, String.class, ParameterMode.IN)
                .setParameter(USER_JSON, JsonUtil.objectToJson(userDto));
        return (UserResponseDto) query.getSingleResult();
    }

    @Override
    public TokenResponse login(UserLogin userLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.getUsername(),
                            userLogin.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return new TokenResponse(jwt);
        } catch (BadCredentialsException exception) {
            throw new OctException(ErrorMessages.PASSWORD_LOGIN_FAIL);
        } catch (Exception exception) {
            throw new OctException(ErrorMessages.USERNAME_LOGIN_FAIL);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserResponseDto> getAllLeader() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_LEADER, USER_RESPONSE_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public UserResponseDto getLeaderById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_LEADER_BY_ID, USER_RESPONSE_DTO_MAPPER)
                .registerStoredProcedureParameter(USER_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(USER_ID_PARAM, id);
        try {
            return (UserResponseDto) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }
}
