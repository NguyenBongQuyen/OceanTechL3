package com.octl3.api.service.Impl;

import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EntityManager entityManager;

    @Override
    public RegistrationDto create(RegistrationDto registrationDto) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_registration", RegistrationDto.class)
                    .registerStoredProcedureParameter("registration_data_json", String.class, ParameterMode.IN)
                    .setParameter("registration_data_json", JsonUtil.objectToJson(registrationDto));
            query.execute();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return registrationDto;
    }

    @Override
    public List<RegistrationDto> getAll() {
        List<RegistrationDto> registrationDtoList = new ArrayList<>();
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_registration",
                    "RegistrationDtoMapper");
//            query.execute();
            registrationDtoList = query.getResultList();
            log.info(registrationDtoList.toString());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return registrationDtoList;
    }

    @Override
    public RegistrationDto getById(Long id) {
        return null;
    }

    @Override
    public List<RegistrationDto> getByStatus(String status) {
        return null;
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
