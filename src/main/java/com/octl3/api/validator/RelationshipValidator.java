package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.constants.StoredProcedure.Relationship;
import com.octl3.api.dto.RelationshipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.octl3.api.constants.Const.EXISTS_VALUE;

@Component
@RequiredArgsConstructor
public class RelationshipValidator {
    private final EntityManager entityManager;
    private final CommonValidator commonValidator;

    public void existsById(long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.EXISTS_BY_ID)
                        .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != Const.EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND_RELATIONSHIP_ID);
        }
    }

    public void checkCreate(RelationshipDto relationshipDto) {
        commonValidator.checkDateOfBirth(relationshipDto.getDateOfBirth());
        if (isPhoneExists(relationshipDto.getPhone())) {
            throw new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if (isCitizenIdExists(relationshipDto.getCitizenId())) {
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }

    public void checkUpdate(RelationshipDto relationshipDto, RelationshipDto existingRelationshipDto) {
        if (!existingRelationshipDto.getPhone().equals(relationshipDto.getPhone()) && isPhoneExists(relationshipDto.getPhone())) {
            throw new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if (!existingRelationshipDto.getCitizenId().equals(relationshipDto.getCitizenId()) && isCitizenIdExists(relationshipDto.getCitizenId())) {
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }

    private boolean isPhoneExists(String phone) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(Relationship.EXISTS_BY_PHONE)
                .registerStoredProcedureParameter(Parameter.PHONE_PARAM, String.class, ParameterMode.IN)
                .setParameter(Parameter.PHONE_PARAM, phone);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }

    private boolean isCitizenIdExists(String citizenId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(Relationship.EXISTS_BY_CITIZEN_ID)
                .registerStoredProcedureParameter(Parameter.CITIZEN_ID_PARAM, String.class, ParameterMode.IN)
                .setParameter(Parameter.CITIZEN_ID_PARAM, citizenId);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }
}