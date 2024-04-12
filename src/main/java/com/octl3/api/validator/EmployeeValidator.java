package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.StoredProcedure.Employee;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {
    private final EntityManager entityManager;

    public void existsById(long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.EXISTS_BY_ID, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != Const.EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }
}
