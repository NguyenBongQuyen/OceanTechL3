package com.octl3.api.security;

import com.octl3.api.dto.user.UserDto;
import com.octl3.api.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static com.octl3.api.constants.SecurityConst.ROLE_PREFIX;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserDto userDto;
    private final RoleService roleService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.isNull(userDto.getRoleId())) {
            return Collections.emptyList();
        } else {
            return Collections.singleton(new SimpleGrantedAuthority(ROLE_PREFIX + roleService.getRoleById(userDto.getRoleId()).getName()));
        }
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userDto.getId();
    }
}
