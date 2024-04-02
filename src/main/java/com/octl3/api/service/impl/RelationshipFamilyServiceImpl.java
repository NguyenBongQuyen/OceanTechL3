package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.*;
import com.octl3.api.dto.RelationshipFamilyDto;
import com.octl3.api.service.RelationshipFamilyService;
import com.octl3.api.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationshipFamilyServiceImpl implements RelationshipFamilyService {
    private final EntityManager entityManager;

    @Override
    public RelationshipFamilyDto create(RelationshipFamilyDto relationshipFamilyDto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.CREATE, Mapper.RELATIONSHIP_FAMILY_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipFamilyDto));
        query.execute();
        return relationshipFamilyDto;
    }

    @Override
    public RelationshipFamilyDto getById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_BY_ID, Mapper.RELATIONSHIP_FAMILY_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        return (RelationshipFamilyDto) query.getSingleResult();
    }

    @Override
    public List<RelationshipFamilyDto> getAll() {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_ALL, Mapper.RELATIONSHIP_FAMILY_DTO_MAPPER);
        @SuppressWarnings("unchecked")
        List<RelationshipFamilyDto> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public RelationshipFamilyDto update(int id, RelationshipFamilyDto relationshipFamilyDto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.UPDATE, Mapper.RELATIONSHIP_FAMILY_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipFamilyDto));
        query.execute();
        return relationshipFamilyDto;
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
