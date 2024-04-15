package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.StoredProcedure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class RegistrationValidator {
    private final EntityManager entityManager;

    public void existsById(long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Registration.EXISTS_BY_ID)
                        .registerStoredProcedureParameter(Parameter.REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.REGISTRATION_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != Const.EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND_REGISTRATION_ID);
        }
    }
}
