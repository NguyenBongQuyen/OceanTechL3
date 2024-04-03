package com.octl3.api.mapper;

import javax.persistence.ColumnResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "EndOfEmployeeDtoMapper",
        columns = {
                @ColumnResult(name = "end_of_employee_id", type = Integer.class),
                @ColumnResult(name = "registration_id", type = Integer.class),
                @ColumnResult(name = "leader_id", type = Integer.class),
                @ColumnResult(name = "end_date", type = LocalDate.class),
                @ColumnResult(name = "end_by", type = String.class),
                @ColumnResult(name = "reason", type = String.class),
                @ColumnResult(name = "status", type = String.class),
                @ColumnResult(name = "reject_date", type = LocalDate.class),
                @ColumnResult(name = "reject_reason", type = String.class),
                @ColumnResult(name = "accept_date", type = LocalDate.class),
                @ColumnResult(name = "storage_number", type = Integer.class)
        }
)
public class EndOfEmployeeDtoMapper {

}
