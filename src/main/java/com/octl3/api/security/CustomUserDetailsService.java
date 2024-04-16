package com.octl3.api.security;

import com.octl3.api.dto.user.UserDto;
import com.octl3.api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.octl3.api.constants.StoredProcedure.Mapper.USER_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.USERNAME_PARAM;
import static com.octl3.api.constants.StoredProcedure.User.GET_USER_BY_USERNAME;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EntityManager entityManager;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_USER_BY_USERNAME, USER_DTO_MAPPER)
                .registerStoredProcedureParameter(USERNAME_PARAM, String.class, ParameterMode.IN)
                .setParameter(USERNAME_PARAM, username);
        UserDto userDto = (UserDto) query.getSingleResult();
        return new CustomUserDetails(userDto, roleService);
    }

}
