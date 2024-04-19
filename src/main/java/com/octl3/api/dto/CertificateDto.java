package com.octl3.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDto {
    private Long id;
    @NotNull(message = "Employee id must not be null")
    private Long employeeId;
    private String name;
    private String field;
    private String content;
    private LocalDate startDate;

}
