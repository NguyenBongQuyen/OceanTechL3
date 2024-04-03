package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import com.octl3.api.dto.SalaryIncrementDto;
import com.octl3.api.service.SalaryIncrementService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.octl3.api.constants.Status.ACCEPTED;
import static com.octl3.api.constants.Status.REJECTED;
import static com.octl3.api.constants.StoredProcedure.Mapper.SALARY_INCREMENT_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.SalaryIncrement.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryIncrementServiceImpl implements SalaryIncrementService {

    private final EntityManager entityManager;

    @Override
    public SalaryIncrementDto create(SalaryIncrementDto salaryIncrementDto) {
        salaryIncrementDto.setCreateDate(LocalDate.now());
        // set create by
        salaryIncrementDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        query.execute();
        return salaryIncrementDto;
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
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_SALARY_INCREMENT_BY_STATUS, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public SalaryIncrementDto updateByManager(long id, SalaryIncrementDto salaryIncrementDto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_SALARY_INCREMENT_BY_MANAGER, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id)
                .registerStoredProcedureParameter(SALARY_INCREMENT_JSON, String.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_JSON, JsonUtil.objectToJson(salaryIncrementDto));
        query.execute();
        return salaryIncrementDto;
    }

    @Override
    public void submit(long id, SalaryIncrementDto salaryIncrementDto) {
        salaryIncrementDto.setStatus(Status.SUBMITTED.getValue());
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
        query.execute();
        return salaryIncrementDto;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_SALARY_INCREMENT, SALARY_INCREMENT_DTO_MAPPER)
                .registerStoredProcedureParameter(SALARY_INCREMENT_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(SALARY_INCREMENT_ID_PARAM, id);
        int rowEffect;
        try {
            rowEffect = query.executeUpdate();
        } catch (Exception e) {
            throw new OctException(ErrorMessages.NOT_ALLOW);
        }
        if (rowEffect == 0) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

}