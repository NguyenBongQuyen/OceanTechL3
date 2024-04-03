package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.constants.StoredProcedure.Relationship;
import com.octl3.api.dto.RelationshipDto;
import com.octl3.api.service.RelationshipService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationshipServiceImpl implements RelationshipService {
    private final EntityManager entityManager;

    @Override
    public RelationshipDto create(RelationshipDto relationshipDto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.CREATE, Mapper.RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        query.execute();
        return relationshipDto;
    }

    @Override
    public RelationshipDto getById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_BY_ID, Mapper.RELATIONSHIP_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        return (RelationshipDto) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RelationshipDto> getByEmployeeId(int employeeId) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_BY_EMPLOYEE_ID, Mapper.RELATIONSHIP_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, employeeId);
        return query.getResultList();
    }

    @Override
    public RelationshipDto update(int id, RelationshipDto relationshipDto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.UPDATE, Mapper.RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        query.execute();
        return relationshipDto;
    }

    @Override
    public void deleteById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.DELETE)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        query.execute();
    }
}
