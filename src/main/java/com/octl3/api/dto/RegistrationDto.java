package com.octl3.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {

    private Long id;
    private Long employeeId;
    private Long leaderId;
    private LocalDate createDate;
    private String createBy;
    private String content;
    private String status;
    private LocalDate submitDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private String note;

}
