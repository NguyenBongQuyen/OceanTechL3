package com.octl3.api.service;

import com.octl3.api.dto.CertificateDto;

import java.util.List;

public interface CertificateService {
    CertificateDto create(CertificateDto certificateDto);
    CertificateDto getById(int id);
    List<CertificateDto> getByEmployeeId(int employeeId);
    CertificateDto update(int id, CertificateDto certificateDto);
    void deleteById(int id);
}
