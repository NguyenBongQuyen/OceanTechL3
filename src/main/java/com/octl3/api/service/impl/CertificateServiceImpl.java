package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Certificate;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
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
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.CREATE, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        query.execute();
        return certificateDto;
    }

    @Override
    public CertificateDto getById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.GET_BY_ID, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        return (CertificateDto) query.getSingleResult();
    }

    @Override
    public List<CertificateDto> getAll() {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.GET_ALL, Mapper.CERTIFICATE_DTO_MAPPER);
        @SuppressWarnings("unchecked")
        List<CertificateDto> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public CertificateDto update(int id, CertificateDto certificateDto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.UPDATE, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        query.execute();
        return certificateDto;
    }

    @Override
    public void deleteById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.DELETE)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        query.execute();
    }
}
