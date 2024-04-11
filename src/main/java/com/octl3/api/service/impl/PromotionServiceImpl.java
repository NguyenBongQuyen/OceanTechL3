package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import com.octl3.api.dto.PromotionDto;
import com.octl3.api.service.PromotionService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

import static com.octl3.api.constants.Status.ACCEPTED;
import static com.octl3.api.constants.Status.REJECTED;
import static com.octl3.api.constants.StoredProcedure.Mapper.PROMOTION_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.Promotion.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final EntityManager entityManager;
    private final UserValidator userValidator;

    @Override
    public PromotionDto create(PromotionDto promotionDto) {
        promotionDto.setCreateDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        promotionDto.setCreateBy(authentication.getName());
        promotionDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_PROMOTION, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_JSON, String.class, ParameterMode.IN)
                .setParameter(PROMOTION_JSON, JsonUtil.objectToJson(promotionDto));
        return (PromotionDto) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PromotionDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_PROMOTION, PROMOTION_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public PromotionDto getById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROMOTION_BY_ID, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROMOTION_ID_PARAM, id);
        try {
            return (PromotionDto) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PromotionDto> getByStatus(String status) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROMOTION_BY_STATUS, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public PromotionDto updateByManager(long id, PromotionDto promotionDto) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_PROMOTION_BY_MANAGER, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROMOTION_ID_PARAM, id)
                .registerStoredProcedureParameter(PROMOTION_JSON, String.class, ParameterMode.IN)
                .setParameter(PROMOTION_JSON, JsonUtil.objectToJson(promotionDto));
        return (PromotionDto) query.getSingleResult();
    }

    @Override
    public void submit(long id, PromotionDto promotionDto) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        promotionDto.setStatus(Status.SUBMITTED.getValue());
        promotionDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_PROMOTION, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROMOTION_ID_PARAM, id)
                .registerStoredProcedureParameter(PROMOTION_JSON, String.class, ParameterMode.IN)
                .setParameter(PROMOTION_JSON, JsonUtil.objectToJson(promotionDto));
        query.execute();
    }

    @Override
    public PromotionDto updateByLeader(long id, PromotionDto promotionDto) {
        userValidator.checkIsForLeader(getById(id).getLeaderId());
        if (promotionDto.getStatus().equals(ACCEPTED.getValue())) {
            promotionDto.setAcceptDate(LocalDate.now());

        }
        if (promotionDto.getStatus().equals(REJECTED.getValue())) {
            promotionDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_PROMOTION_BY_LEADER, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROMOTION_ID_PARAM, id)
                .registerStoredProcedureParameter(PROMOTION_JSON, String.class, ParameterMode.IN)
                .setParameter(PROMOTION_JSON, JsonUtil.objectToJson(promotionDto));
        return (PromotionDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_PROMOTION, PROMOTION_DTO_MAPPER)
                .registerStoredProcedureParameter(PROMOTION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROMOTION_ID_PARAM, id);
        try {
            query.execute();
        } catch (Exception e) {
            throw new OctException(ErrorMessages.DELETE_ERROR);
        }
    }

}
