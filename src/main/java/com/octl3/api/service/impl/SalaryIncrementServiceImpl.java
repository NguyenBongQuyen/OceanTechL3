package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import com.octl3.api.dto.SalaryIncrementDto;
import com.octl3.api.service.SalaryIncrementService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.StatusValidator;
import com.octl3.api.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

import static com.octl3.api.constants.Status.*;
import static com.octl3.api.constants.StoredProcedure.Mapper.SALARY_INCREMENT_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.SalaryIncrement.*;

@Service
@RequiredArgsConstructor
public class SalaryIncrementServiceImpl implements SalaryIncrementService {

    private final EntityManager entityManager;
    private final UserValidator userValidator;
    private final StatusValidator statusValidator;
    private final EmployeeValidator employeeValidator;

    @Override
    public SalaryIncrementDto create(SalaryIncrementDto salaryIncrementDto) {
        employeeValidator.existsById(salaryIncrementDto.getEmployeeId());
        salaryIncrementDto.setCreateDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        salaryIncrementDto.setCreateBy(authentication.getName());
        salaryIncrementDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        return (SalaryIncrementDto) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalaryIncrementDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public SalaryIncrementDto getById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_SALARY_INCREMENT_BY_ID, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id);
        try {
            return (SalaryIncrementDto) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalaryIncrementDto> getByStatus(String status) {
        statusValidator.checkValidStatus(status);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_SALARY_INCREMENT_BY_STATUS, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public SalaryIncrementDto updateByManager(long id, SalaryIncrementDto salaryIncrementDto) {
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        employeeValidator.existsById(salaryIncrementDto.getEmployeeId());
        salaryIncrementDto.setStatus(UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_SALARY_INCREMENT_BY_MANAGER, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        return (SalaryIncrementDto) query.getSingleResult();
    }

    @Override
    public void submit(long id, SalaryIncrementDto salaryIncrementDto) {
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        userValidator.checkExistLeaderId(salaryIncrementDto.getLeaderId());
        salaryIncrementDto.setStatus(Status.PENDING.getValue());
        salaryIncrementDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        query.execute();
    }

    @Override
    public SalaryIncrementDto updateByLeader(long id, SalaryIncrementDto salaryIncrementDto) {
        userValidator.checkIsForLeader(this.getById(id).getLeaderId());
        statusValidator.checkValidLeaderStatus(salaryIncrementDto.getStatus());
        if (salaryIncrementDto.getStatus().equals(ACCEPTED.getValue())) {
            salaryIncrementDto.setAcceptDate(LocalDate.now());
        }
        if (salaryIncrementDto.getStatus().equals(REJECTED.getValue())) {
            salaryIncrementDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_SALARY_INCREMENT_BY_LEADER, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        return (SalaryIncrementDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id);
        try {
            query.execute();
        } catch (Exception e) {
            throw new OctException(ErrorMessages.DELETE_ERROR);
        }
    }
}
