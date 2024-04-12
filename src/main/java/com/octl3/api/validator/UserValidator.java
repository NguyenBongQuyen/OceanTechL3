package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.User.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final EntityManager entityManager;

    public void checkIsForLeader(Long leaderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        if (!userId.equals(leaderId)) {
            throw new OctException(ErrorMessages.NOT_ALLOW);
        }
    }

    public void checkCreateByManager(String createBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals(createBy)) {
            throw new OctException(ErrorMessages.NOT_ALLOW);
        }
    }

    public void checkUserRegister(UserDto userDto) {
        if (isExistUsername(userDto.getUsername())) {
            throw new OctException(ErrorMessages.DUPLICATE_USERNAME);
        }
        if (isExistEmail(userDto.getEmail())) {
            throw new OctException(ErrorMessages.DUPLICATE_EMAIL);
        }
    }

    private boolean isExistUsername(String username) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(IS_EXIST_USERNAME)
                .registerStoredProcedureParameter(USERNAME_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(USERNAME_PARAM, username);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == 1;
    }

    private boolean isExistEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(IS_EXIST_EMAIL)
                .registerStoredProcedureParameter(EMAIL_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(EMAIL_PARAM, email);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == 1;
    }
}
