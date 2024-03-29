package com.octl3.api.mapper;

import com.octl3.api.dto.EmployeeDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "EmployeeDtoMapper",
        classes = @ConstructorResult(
                targetClass = EmployeeDto.class,
                columns = {
                        @ColumnResult(name = "employee_id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "code", type = String.class),
                        @ColumnResult(name = "date_of_birth", type = LocalDate.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "phone", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "citizen_id", type = String.class),
                        @ColumnResult(name = "team", type = String.class),
                        @ColumnResult(name = "create_by", type = String.class)
                }
        )
)
public class EmployeeMapper {

}
