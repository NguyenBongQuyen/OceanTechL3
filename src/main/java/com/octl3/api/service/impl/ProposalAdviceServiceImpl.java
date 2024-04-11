package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Status;
import com.octl3.api.dto.ProposalAdviceDto;
import com.octl3.api.service.ProposalAdviceService;
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
import static com.octl3.api.constants.StoredProcedure.Mapper.PROPOSAL_ADVICE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.ProposalAdvice.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalAdviceServiceImpl implements ProposalAdviceService {

    private final EntityManager entityManager;
    private final UserValidator userValidator;

    @Override
    public ProposalAdviceDto create(ProposalAdviceDto proposalAdviceDto) {
        proposalAdviceDto.setCreateDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        proposalAdviceDto.setCreateBy(authentication.getName());
        proposalAdviceDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_PROPOSAL_ADVICE, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_JSON, String.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_JSON, JsonUtil.objectToJson(proposalAdviceDto));
        return (ProposalAdviceDto) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProposalAdviceDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_PROPOSAL_ADVICE, PROPOSAL_ADVICE_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public ProposalAdviceDto getById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROPOSAL_ADVICE_BY_ID, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_ID_PARAM, id);
        try {
            return (ProposalAdviceDto) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProposalAdviceDto> getByStatus(String status) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROPOSAL_ADVICE_BY_STATUS, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public ProposalAdviceDto updateByManager(long id, ProposalAdviceDto proposalAdviceDto) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_PROPOSAL_ADVICE_BY_MANAGER, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_ID_PARAM, id)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_JSON, String.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_JSON, JsonUtil.objectToJson(proposalAdviceDto));
        return (ProposalAdviceDto) query.getSingleResult();
    }

    @Override
    public void submit(long id, ProposalAdviceDto proposalAdviceDto) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        proposalAdviceDto.setStatus(Status.SUBMITTED.getValue());
        proposalAdviceDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_PROPOSAL_ADVICE, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_ID_PARAM, id)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_JSON, String.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_JSON, JsonUtil.objectToJson(proposalAdviceDto));
        query.execute();
    }

    @Override
    public ProposalAdviceDto updateByLeader(long id, ProposalAdviceDto proposalAdviceDto) {
        userValidator.checkIsForLeader(getById(id).getLeaderId());
        if (proposalAdviceDto.getStatus().equals(ACCEPTED.getValue())) {
            proposalAdviceDto.setAcceptDate(LocalDate.now());
        }
        if (proposalAdviceDto.getStatus().equals(REJECTED.getValue())) {
            proposalAdviceDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_PROPOSAL_ADVICE_BY_LEADER, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_ID_PARAM, id)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_JSON, String.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_JSON, JsonUtil.objectToJson(proposalAdviceDto));
        return (ProposalAdviceDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_PROPOSAL_ADVICE, PROPOSAL_ADVICE_DTO_MAPPER)
                .registerStoredProcedureParameter(PROPOSAL_ADVICE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROPOSAL_ADVICE_ID_PARAM, id);
        try {
            query.execute();
        } catch (Exception e) {
            throw new OctException(ErrorMessages.DELETE_ERROR);
        }
    }
}
