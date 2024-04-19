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
public class ProfileEndDto {
    private Long id;
    @NotNull(message = "Registration id must not be null")
    private Long registrationId;
    private Long leaderId;
    private LocalDate endDate;
    private String endBy;
    private String reason;
    private String status;
    private LocalDate submitDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private Long storageNumber;

}
