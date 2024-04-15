package com.octl3.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octl3.api.validator.anotations.CitizenId;
import com.octl3.api.validator.anotations.Phone;
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
public class RelationshipDto {
    private Long id;
    private Long employeeId;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    @Phone
    private String phone;
    private String address;
    @CitizenId
    private String citizenId;
    private String relationship;
}
