package com.octl3.api.mapper;

import com.octl3.api.dto.ProfileEndDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "ProfileEndDtoMapper",
        classes = @ConstructorResult(
                targetClass = ProfileEndDto.class,
                columns = {
                        @ColumnResult(name = "profile_end_id", type = Long.class),
                        @ColumnResult(name = "registration_id", type = Long.class),
                        @ColumnResult(name = "leader_id", type = Long.class),
                        @ColumnResult(name = "end_date", type = LocalDate.class),
                        @ColumnResult(name = "end_by", type = String.class),
                        @ColumnResult(name = "reason", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submit_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "storage_number", type = Long.class)
                }
        )
)
public class ProfileEndDtoMapper {

}
