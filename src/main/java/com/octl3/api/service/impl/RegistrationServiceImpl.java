package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
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

import static com.octl3.api.constants.Status.*;
import static com.octl3.api.constants.StoredProcedure.Mapper.REGISTRATION_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.Registration.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EntityManager entityManager;

    @Override
    public RegistrationDto create(RegistrationDto registrationDto) {
        registrationDto.setCreateDate(LocalDate.now());
        // set create by?
        registrationDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_JSON, String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
        return registrationDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RegistrationDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_REGISTRATION, REGISTRATION_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public RegistrationDto getById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_REGISTRATION_BY_ID, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id);
        try {
            return (RegistrationDto) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RegistrationDto> getByStatus(String status) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_REGISTRATION_BY_STATUS, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public RegistrationDto updateByManager(long id, RegistrationDto registrationDto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_REGISTRATION_BY_MANAGER, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON, String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
        return registrationDto;
    }

    @Override
    public void submit(long id, RegistrationDto registrationDto) {
        registrationDto.setStatus(Status.SUBMITTED.getValue());
        registrationDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON, String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
    }

    @Override
    public RegistrationDto updateByLeader(long id, RegistrationDto registrationDto) {
        if (registrationDto.getStatus().equals(ACCEPTED.getValue())) {
            registrationDto.setAcceptDate(LocalDate.now());
        }
        if (registrationDto.getStatus().equals(REJECTED.getValue())) {
            registrationDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_REGISTRATION_BY_LEADER, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON, String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
        return registrationDto;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id);
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
