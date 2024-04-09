package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    @PostMapping
    public DataResponse<CertificateDto> create(@RequestBody CertificateDto certificateDto) {
        return DataResponse.ok(certificateService.create(certificateDto));
    }

    @GetMapping("/{id}")
    public DataResponse<CertificateDto> getById(@PathVariable("id") int id) {
        return DataResponse.ok(certificateService.getById(id));
    }

    @GetMapping
    public DataResponse<List<CertificateDto>> getByEmployeeId(@RequestParam("employeeId") int employeeId) {
        return DataResponse.ok(certificateService.getByEmployeeId(employeeId));
    }

    @PutMapping("/{id}")
    public DataResponse<CertificateDto> update(@PathVariable("id") int id,
                                               @RequestBody CertificateDto certificateDto) {
        return DataResponse.ok(certificateService.update(id, certificateDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") int id) {
        certificateService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }

}
