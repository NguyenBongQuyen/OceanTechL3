package com.octl3.api.dto;

import com.octl3.api.validator.anotations.CitizenId;
import com.octl3.api.validator.anotations.Phone;
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
public class RelationshipDto {
    private Long id;
    @NotNull(message = "Employee id must not be null")
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
