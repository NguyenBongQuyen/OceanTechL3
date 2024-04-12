package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctNotFoundException;
import com.octl3.api.commons.suberror.ApiSubError;
import com.octl3.api.commons.suberror.ApiValidatorError;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.FieldName;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.constants.StoredProcedure.Certificate;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class CertificateValidator {
    private final EntityManager entityManager;

    public void existsById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Certificate.EXISTS_BY_ID, Mapper.CERTIFICATE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.CERTIFICATE_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.CERTIFICATE_ID_PARAM, id);
        Integer result = (Integer) query.getOutputParameterValue(Parameter.RESULT);
        if (result != Const.EXISTS_CERTIFICATE) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.CERTIFICATE_ID, id, MessageConst.Certificate.NOT_FOUND);
            throw new OctNotFoundException(ErrorMessages.NOT_FOUND, apiSubError);
        }
    }
}
