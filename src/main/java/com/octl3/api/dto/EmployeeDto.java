package com.octl3.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octl3.api.validator.anotations.CitizenId;
import com.octl3.api.validator.anotations.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
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
    @Phone
    private String phone;
    @Email
    private String email;
    private String address;
    @CitizenId
    private String citizenId;
    private String team;
    private String createBy;

}
