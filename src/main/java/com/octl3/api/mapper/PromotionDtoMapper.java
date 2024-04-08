package com.octl3.api.mapper;

import com.octl3.api.dto.PromotionDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "PromotionDtoMapper",
        classes = @ConstructorResult(
                targetClass = PromotionDto.class,
                columns = {
                        @ColumnResult(name = "promotion_id", type = Long.class),
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "leader_id", type = Long.class),
                        @ColumnResult(name = "create_date", type = LocalDate.class),
                        @ColumnResult(name = "create_by", type = String.class),
                        @ColumnResult(name = "times", type = Integer.class),
                        @ColumnResult(name = "reason", type = String.class),
                        @ColumnResult(name = "new_position", type = String.class),
                        @ColumnResult(name = "old_position", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submit_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "note", type = String.class)
                }
        )
)
public class PromotionDtoMapper {
}
