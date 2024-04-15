package com.octl3.api.mapper;

import com.octl3.api.dto.RelationshipDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "RelationshipDtoMapper",
        classes = @ConstructorResult(
                targetClass = RelationshipDto.class,
                columns = {
                        @ColumnResult(name = "relationship_id", type = Long.class),
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "date_of_birth", type = LocalDate.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "phone", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "citizen_id", type = String.class),
                        @ColumnResult(name = "relationship", type = String.class)
                }
        )
)
public class RelationshipDtoMapper {
}
