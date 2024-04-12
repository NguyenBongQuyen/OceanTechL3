package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctNotFoundException;
import com.octl3.api.commons.suberror.ApiSubError;
import com.octl3.api.commons.suberror.ApiValidatorError;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.FieldName;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.constants.StoredProcedure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class ProfileEndValidator {
    private final EntityManager entityManager;

    public void existsById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(StoredProcedure.ProfileEnd.EXISTS_BY_ID, StoredProcedure.Mapper.PROFILE_END_DTO_MAPPER)
                        .registerStoredProcedureParameter(StoredProcedure.Parameter.PROFILE_END_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(StoredProcedure.Parameter.PROFILE_END_ID_PARAM, id)
                        .registerStoredProcedureParameter(StoredProcedure.Parameter.RESULT, Integer.class, ParameterMode.OUT);
        Integer result = (Integer) query.getOutputParameterValue(StoredProcedure.Parameter.RESULT);
        if (result != Const.EXISTS_PROFILE_END) {
            ApiSubError apiSubError = new ApiValidatorError(FieldName.PROFILE_END_ID, id, MessageConst.ProfileEnd.NOT_FOUND);
            throw new OctNotFoundException(ErrorMessages.NOT_FOUND, apiSubError);
        }
    }
}
