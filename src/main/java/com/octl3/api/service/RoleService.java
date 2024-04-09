package com.octl3.api.service;

import com.octl3.api.dto.RoleDto;

public interface RoleService {
    RoleDto getRoleByName(String roleName);
    boolean isExistRoleById(int id);
    RoleDto getRoleById(int id);

}
