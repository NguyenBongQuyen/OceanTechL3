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
    private Integer id;
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

    public EmployeeDto(EmployeeProfileDto employeeProfileDto) {
        if (employeeProfileDto == null) {
            return;
        }
        this.name = employeeProfileDto.getName();
        this.code = employeeProfileDto.getCode();
        this.dateOfBirth = employeeProfileDto.getDateOfBirth();
        this.gender = employeeProfileDto.getGender();
        this.phone = employeeProfileDto.getPhone();
        this.email = employeeProfileDto.getEmail();
        this.address = employeeProfileDto.getAddress();
        this.citizenId = employeeProfileDto.getCitizenId();
        this.team = employeeProfileDto.getTeam();
        this.createBy = employeeProfileDto.getCreateBy();
    }

}
