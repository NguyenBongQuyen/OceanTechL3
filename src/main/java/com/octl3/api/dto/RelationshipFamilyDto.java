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
public class RelationshipFamilyDto {
    private Integer id;
    private Integer employeeId;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String address;
    private String citizenId;
    private String relationship;

}
