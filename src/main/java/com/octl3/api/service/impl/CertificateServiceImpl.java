package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Certificate;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.CertificateValidator;
import com.octl3.api.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final EntityManager entityManager;
    private final CertificateValidator certificateValidator;
    private final EmployeeValidator employeeValidator;

    @Override
    public CertificateDto create(CertificateDto certificateDto) {
        employeeValidator.existsById(certificateDto.getEmployeeId());
        certificateValidator.checkCreateAndUpdate(certificateDto);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.CREATE, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        return (CertificateDto) query.getSingleResult();
    }

    @Override
    public CertificateDto getById(long id) {
        certificateValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.GET_BY_ID, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        return (CertificateDto) query.getSingleResult();
    }

    @Override
    public List<CertificateDto> getByEmployeeId(long employeeId) {
        employeeValidator.existsById(employeeId);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.GET_BY_EMPLOYEE_ID, Mapper.CERTIFICATE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, employeeId);
        return castResultListToListCertificateDto(query.getResultList());
    }

    private List<CertificateDto> castResultListToListCertificateDto(List<?> resultList) {
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        for (Object object : resultList) {
            if (object instanceof CertificateDto) {
                certificateDtoList.add((CertificateDto) object);
            }
        }
        return certificateDtoList;
    }

    @Override
    public CertificateDto update(long id, CertificateDto certificateDto) {
        certificateValidator.existsById(id);
        employeeValidator.existsById(certificateDto.getEmployeeId());
        certificateValidator.checkCreateAndUpdate(certificateDto);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.UPDATE, Mapper.CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        return (CertificateDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        certificateValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.DELETE)
                .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        query.execute();
    }
}
