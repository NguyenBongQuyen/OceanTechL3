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
public class ProfileEndDto {
    private Integer id;
    private Integer registrationId;
    private Integer leaderId;
    private LocalDate endDate;
    private String endBy;
    private String reason;
    private String status;
    private LocalDate submitDate;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private Integer storageNumber;

}
