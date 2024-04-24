package com.octl3.api.service.impl;

import com.octl3.api.constants.Status;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.constants.StoredProcedure.ProfileEnd;
import com.octl3.api.dto.ProfileEndDto;
import com.octl3.api.service.ProfileEndService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.ProfileEndValidator;
import com.octl3.api.validator.RegistrationValidator;
import com.octl3.api.validator.StatusValidator;
import com.octl3.api.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileEndServiceImpl implements ProfileEndService {
    private final EntityManager entityManager;
    private final ProfileEndValidator profileEndValidator;
    private final UserValidator userValidator;
    private final RegistrationValidator registrationValidator;
    private final StatusValidator statusValidator;

    @Override
    public ProfileEndDto create(ProfileEndDto profileEndDto) {
        registrationValidator.existsById(profileEndDto.getRegistrationId());
        profileEndDto.setEndDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        profileEndDto.setEndBy(authentication.getName());
        profileEndDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.CREATE, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public ProfileEndDto getById(long id) {
        profileEndValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.GET_BY_ID, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_ID_PARAM, id);
        return (ProfileEndDto) query.getSingleResult();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfileEndDto> getAll() {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.GET_ALL, Mapper.PROFILE_END_DTO_MAPPER);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfileEndDto> getByStatus(String status) {
        statusValidator.checkValidStatus(status);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.GET_BY_STATUS, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.STATUS_PARAM, String.class, ParameterMode.IN)
                        .setParameter(Parameter.STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public ProfileEndDto updateByManager(long id, ProfileEndDto profileEndDto) {
        userValidator.checkCreateByManager(this.getById(id).getEndBy());
        registrationValidator.existsById(profileEndDto.getRegistrationId());
        profileEndDto.setStatus(Status.UPDATED.getValue());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.UPDATE_BY_MANAGER, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public void submit(long id, ProfileEndDto profileEndDto) {
        userValidator.checkCreateByManager(this.getById(id).getEndBy());
        userValidator.checkExistLeaderId(profileEndDto.getLeaderId());
        profileEndDto.setStatus(Status.PENDING.getValue());
        profileEndDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.SUBMIT, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        query.execute();
    }

    @Override
    public ProfileEndDto updateByLeader(long id, ProfileEndDto profileEndDto) {
        ProfileEndDto existedProfileEndDto = this.getById(id);
        userValidator.checkIsForLeader(existedProfileEndDto.getLeaderId());
        statusValidator.checkValidLeaderStatus(profileEndDto.getStatus());
        if (profileEndDto.getStatus().equals(Status.ACCEPTED.getValue())) {
            profileEndDto.setAcceptDate(LocalDate.now());
        }
        if (profileEndDto.getStatus().equals(Status.REJECTED.getValue())) {
            profileEndDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.UPDATE_BY_LEADER, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        profileEndValidator.existsById(id);
        userValidator.checkCreateByManager(this.getById(id).getEndBy());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.DELETE, Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.PROFILE_END_ID_PARAM, id);
        query.execute();
    }
}
