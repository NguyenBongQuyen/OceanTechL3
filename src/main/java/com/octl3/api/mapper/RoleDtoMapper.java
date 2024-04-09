package com.octl3.api.mapper;


import com.octl3.api.dto.RoleDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
        name = "RoleDtoMapper",
        classes = @ConstructorResult(
                targetClass = RoleDto.class,
                columns = {
                        @ColumnResult(name = "role_id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                }
        )
)
public class RoleDtoMapper {
}
