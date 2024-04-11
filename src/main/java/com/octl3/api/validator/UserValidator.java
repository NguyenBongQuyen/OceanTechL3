package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
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
}
