package com.octl3.api.mapper;

import com.octl3.api.dto.user.UserResponseDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
        name = "UserResponseDtoMapper",
        classes = @ConstructorResult(
                targetClass = UserResponseDto.class,
                columns = {
                        @ColumnResult(name = "username", type = String.class),
//                        @ColumnResult(name = "role_id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "position", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "phone", type = String.class)
                }
        )
)
public class UserResponseDtoMapper {
}
