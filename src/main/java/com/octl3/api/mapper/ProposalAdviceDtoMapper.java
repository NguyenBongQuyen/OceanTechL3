package com.octl3.api.mapper;

import com.octl3.api.dto.ProposalAdviceDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "ProposalAdviceDtoMapper",
        classes = @ConstructorResult(
                targetClass = ProposalAdviceDto.class,
                columns = {
                        @ColumnResult(name = "proposal_advice_id", type = Long.class),
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "leader_id", type = Long.class),
                        @ColumnResult(name = "create_date", type = LocalDate.class),
                        @ColumnResult(name = "create_by", type = String.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submit_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_date", type = LocalDate.class),
                        @ColumnResult(name = "reject_reason", type = String.class),
                        @ColumnResult(name = "accept_date", type = LocalDate.class),
                        @ColumnResult(name = "note", type = String.class)
                }
        )
)
public class ProposalAdviceDtoMapper {
}
