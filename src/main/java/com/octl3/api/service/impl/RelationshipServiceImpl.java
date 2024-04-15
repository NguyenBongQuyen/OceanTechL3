package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.constants.StoredProcedure.Relationship;
import com.octl3.api.dto.RelationshipDto;
import com.octl3.api.service.RelationshipService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.RelationshipValidator;
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
    private final RelationshipValidator relationshipValidator;
    private final EmployeeValidator employeeValidator;

    @Override
    public RelationshipDto create(RelationshipDto relationshipDto) {
        employeeValidator.existsById(relationshipDto.getEmployeeId());
        relationshipValidator.checkCreate(relationshipDto);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.CREATE, Mapper.RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        query.execute();
        return relationshipDto;
    }

    @Override
    public RelationshipDto getById(long id) {
        relationshipValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_BY_ID, Mapper.RELATIONSHIP_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        return (RelationshipDto) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RelationshipDto> getByEmployeeId(long employeeId) {
        employeeValidator.existsById(employeeId);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.GET_BY_EMPLOYEE_ID, Mapper.RELATIONSHIP_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, employeeId);
        return query.getResultList();
    }

    @Override
    public RelationshipDto update(long id, RelationshipDto relationshipDto) {
        employeeValidator.existsById(relationshipDto.getEmployeeId());
        RelationshipDto existingRelationshipDto = this.getById(id);
        relationshipValidator.checkUpdate(relationshipDto, existingRelationshipDto);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.UPDATE, Mapper.RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_JSON, String.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        query.execute();
        return relationshipDto;
    }

    @Override
    public void deleteById(long id) {
        relationshipValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.DELETE)
                .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        query.execute();
    }
}
