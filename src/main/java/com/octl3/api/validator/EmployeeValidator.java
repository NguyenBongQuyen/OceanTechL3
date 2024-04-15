package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.StoredProcedure;
import com.octl3.api.dto.EmployeeDto;
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
    private final CommonValidator commonValidator;

    public void existsById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EXISTS_BY_ID)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (ObjectUtils.isEmpty(result) || result.intValue() != EXISTS_VALUE) {
            throw new OctException(NOT_FOUND_EMPLOYEE_ID);
        }
    }

    public void checkCreate(EmployeeDto employeeDto) {
        commonValidator.checkDateOfBirth(employeeDto.getDateOfBirth());
        if (isPhoneExists(employeeDto.getPhone())) {
            throw new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if (isEmailExists(employeeDto.getEmail())) {
            throw new OctException(ErrorMessages.DUPLICATE_EMAIL);
        }
        if (isCitizenIdExists(employeeDto.getCitizenId())) {
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }

    public void checkUpdate(EmployeeDto employeeDto, EmployeeDto existingEmployeeDto) {
        if (!existingEmployeeDto.getPhone().equals(employeeDto.getPhone()) && isPhoneExists(employeeDto.getPhone())) {
            throw new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if (!existingEmployeeDto.getEmail().equals(employeeDto.getEmail()) && isEmailExists(employeeDto.getEmail())) {
            throw new OctException(ErrorMessages.DUPLICATE_EMAIL);
        }
        if (!existingEmployeeDto.getCitizenId().equals(employeeDto.getCitizenId()) && isCitizenIdExists(employeeDto.getCitizenId())) {
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }

    private boolean isPhoneExists(String phone) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedure.Employee.EXISTS_BY_PHONE)
                .registerStoredProcedureParameter(StoredProcedure.Parameter.PHONE_PARAM, String.class, ParameterMode.IN)
                .setParameter(StoredProcedure.Parameter.PHONE_PARAM, phone);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }

    private boolean isEmailExists(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedure.Employee.EXISTS_BY_EMAIL)
                .registerStoredProcedureParameter(StoredProcedure.Parameter.EMAIL_PARAM, String.class, ParameterMode.IN)
                .setParameter(StoredProcedure.Parameter.EMAIL_PARAM, email);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }

    private boolean isCitizenIdExists(String citizenId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedure.Employee.EXISTS_BY_CITIZEN_ID)
                .registerStoredProcedureParameter(StoredProcedure.Parameter.CITIZEN_ID_PARAM, String.class, ParameterMode.IN)
                .setParameter(StoredProcedure.Parameter.CITIZEN_ID_PARAM, citizenId);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }
}
