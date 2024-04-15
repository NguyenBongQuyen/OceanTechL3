package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.StoredProcedure.Certificate;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.dto.CertificateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class CertificateValidator {
    private final EntityManager entityManager;
    private final CommonValidator commonValidator;

    public void existsById(long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.EXISTS_BY_ID)
                        .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != Const.EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND_CERTIFICATE_ID);
        }
    }

    public void checkCreateAndUpdate(CertificateDto certificateDto) {
        commonValidator.checkDateInTheFuture(certificateDto.getStartDate());
    }
}
