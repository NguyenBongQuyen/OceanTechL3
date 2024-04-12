package com.octl3.api.service;

import com.octl3.api.dto.CertificateDto;

import java.util.List;

public interface CertificateService {
    CertificateDto create(CertificateDto certificateDto);
    CertificateDto getById(long id);
    List<CertificateDto> getByEmployeeId(long employeeId);
    CertificateDto update(long id, CertificateDto certificateDto);
    void deleteById(long id);
}
