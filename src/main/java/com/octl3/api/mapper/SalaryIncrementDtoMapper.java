package com.octl3.api.mapper;

import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.dto.SalaryIncrementDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "SalaryIncrementDtoMapper",
        classes = @ConstructorResult(
                targetClass = SalaryIncrementDto.class,
                columns = {
                        @ColumnResult(name = "salary_increment_id", type = Long.class),
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "leader_id", type = Long.class),
                        @ColumnResult(name = "create_date", type = LocalDate.class),
                        @ColumnResult(name = "create_by", type = String.class),
                        @ColumnResult(name = "times", type = Integer.class),
                        @ColumnResult(name = "reason", type = String.class),
                        @ColumnResult(name = "salary_level", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submit_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "note", type = String.class)
                }
        )
)
public class SalaryIncrementDtoMapper {
}
