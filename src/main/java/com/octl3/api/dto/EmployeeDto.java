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
public class EmployeeDto {
    private Long id;
    private String name;
    private String code;
    private LocalDate dateOfBirth;
    private String gender;
    private String image;
    private String phone;
    private String email;
    private String address;
    private String citizenId;
    private String team;
    private String createBy;

}
