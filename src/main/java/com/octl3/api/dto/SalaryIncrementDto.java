package com.octl3.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryIncrementDto {

    private Long id;
    @NotNull
    private Long employeeId;
    private Long leaderId;
    private LocalDate createDate;
    private String createBy;
    private Integer times;
    private String reason;
    @NotNull
    private String salaryLevel;
    private String status;
    private LocalDate submitDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private String note;

}
