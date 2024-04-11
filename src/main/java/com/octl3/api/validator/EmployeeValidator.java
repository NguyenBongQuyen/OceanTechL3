package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctNotFoundException;
import com.octl3.api.commons.suberror.ApiSubError;
import com.octl3.api.commons.suberror.ApiValidatorError;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.FieldName;
import com.octl3.api.constants.MessageConst;
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

    public void existsById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.EXISTS_BY_ID, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id);
        Integer result = (Integer) query.getOutputParameterValue(Parameter.RESULT);
        if (result != Const.EXISTS_EMPLOYEE) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.EMPLOYEE_ID, id, MessageConst.Employee.NOT_FOUND);
            throw new OctNotFoundException(ErrorMessages.NOT_FOUND, apiSubError);
        }
    }
}
