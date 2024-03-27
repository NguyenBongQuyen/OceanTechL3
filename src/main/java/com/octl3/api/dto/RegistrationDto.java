package com.octl3.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public class RegistrationDto {

    private Long id;
    private Long employeeId;
    private Long leaderId;
    private LocalDate createDate;
    private String createBy;
    private String content;
    private String status;
    private LocalDate rejectDate;
    private String rejectReason;
    private LocalDate acceptDate;
    private String note;

    public RegistrationDto(RegistrationDto registrationDto) {
        this.id = registrationDto.getId();
        this.employeeId = registrationDto.getEmployeeId();
        this.leaderId = registrationDto.getLeaderId();
        this.createDate = registrationDto.getCreateDate();
        this.createBy = registrationDto.getCreateBy();
        this.content = registrationDto.getContent();
        this.status = registrationDto.getStatus();
        this.rejectDate = registrationDto.getRejectDate();
        this.rejectReason = registrationDto.getRejectReason();
        this.acceptDate = registrationDto.getAcceptDate();
        this.note = registrationDto.getNote();
    }

//    public RegistrationDto(Long id, Long employeeId, Long leaderId, LocalDate createDate, String createBy, String content, String status, LocalDate rejectDate, String rejectReason, LocalDate acceptDate, String note) {
//        this.id = id;
//        this.employeeId = employeeId;
//        this.leaderId = leaderId;
//        this.createDate = createDate;
//        this.createBy = createBy;
//        this.content = content;
//        this.status = status;
//        this.rejectDate = rejectDate;
//        this.rejectReason = rejectReason;
//        this.acceptDate = acceptDate;
//        this.note = note;
//    }

}
