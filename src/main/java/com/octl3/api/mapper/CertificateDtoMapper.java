package com.octl3.api.mapper;

import com.octl3.api.dto.CertificateDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "CertificateDtoMapper",
        classes = @ConstructorResult(
                targetClass = CertificateDto.class,
                columns = {
                        @ColumnResult(name = "certificate_id", type = Long.class),
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "field", type = String.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "start_date", type = LocalDate.class)
                }
        )
)
public class CertificateDtoMapper {

}
