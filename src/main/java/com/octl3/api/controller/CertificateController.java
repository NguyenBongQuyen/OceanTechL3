package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class CertificateController {
    private final CertificateService certificateService;
    @PostMapping
    public DataResponse<CertificateDto> create(@RequestBody CertificateDto certificateDto) {
        return DataResponse.ok(certificateService.create(certificateDto));
    }

}
