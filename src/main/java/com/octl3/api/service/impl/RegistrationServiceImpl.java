package com.octl3.api.service.impl;

import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

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
    public RegistrationDto getById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_REGISTRATION_BY_ID, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id);
        return (RegistrationDto) query.getSingleResult();
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
    public RegistrationDto updateByManager(Long id, RegistrationDto registrationDto) {
        return null;
    }

    @Override
    public RegistrationDto updateByLeader(Long id, RegistrationDto registrationDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
