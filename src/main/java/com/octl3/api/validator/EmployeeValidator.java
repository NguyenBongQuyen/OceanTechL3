package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.OctException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.octl3.api.commons.exceptions.ErrorMessages.NOT_FOUND_EMPLOYEE_ID;
import static com.octl3.api.constants.Const.EXISTS_VALUE;
import static com.octl3.api.constants.StoredProcedure.Employee.EXISTS_BY_ID;
import static com.octl3.api.constants.StoredProcedure.Parameter.EMPLOYEE_ID_PARAM;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {
    private final EntityManager entityManager;

    public void existsById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EXISTS_BY_ID)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (ObjectUtils.isEmpty(result) && result.intValue() != EXISTS_VALUE) {
            throw new OctException(NOT_FOUND_EMPLOYEE_ID);
        }
    }
}
