package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.*;
import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final EntityManager entityManager;

    @Override
    public CertificateDto create(CertificateDto certificateDto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(Certificate.CREATE, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.REGISTRATION_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.REGISTRATION_JSON, JsonUtil.objectToJson(certificateDto));
        query.execute();
        return certificateDto;
    }

    @Override
    public CertificateDto getById(int id) {
        return null;
    }

    @Override
    public List<CertificateDto> getAll() {
        return null;
    }

    @Override
    public CertificateDto update(int id, CertificateDto certificateDto) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
