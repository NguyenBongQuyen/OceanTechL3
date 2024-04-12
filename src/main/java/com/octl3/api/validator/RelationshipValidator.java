package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctNotFoundException;
import com.octl3.api.commons.suberror.ApiSubError;
import com.octl3.api.commons.suberror.ApiValidatorError;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.FieldName;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.constants.StoredProcedure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class RelationshipValidator {
    private final EntityManager entityManager;

    public void existsById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.EXISTS_BY_ID, Mapper.RELATIONSHIP_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.RESULT, Integer.class, ParameterMode.OUT);
        Integer result = (Integer) query.getOutputParameterValue(Parameter.RESULT);
        if (result != Const.EXISTS_VALUE) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.RELATIONSHIP_ID, id, MessageConst.Relationship.NOT_FOUND);
            throw new OctNotFoundException(ErrorMessages.NOT_FOUND, apiSubError);
        }
    }

}
