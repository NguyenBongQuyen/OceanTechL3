package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Employee;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.service.EmployeeService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.utils.UploadFile;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

import static com.octl3.api.constants.FileConst.EMPLOYEE_IMAGE_PREFIX;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EntityManager entityManager;
    private final UserValidator userValidator;
    private final EmployeeValidator employeeValidator;

    @Override
    public EmployeeDto create(EmployeeDto employeeDto, MultipartFile fileImage) {
        employeeValidator.checkCreate(employeeDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        employeeDto.setCreateBy(authentication.getName());
        if (!fileImage.isEmpty()) {
            employeeDto.setImage(UploadFile.uploadImage(fileImage, EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.CREATE, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        return (EmployeeDto) query.getSingleResult();
    }

    @Override
    public EmployeeDto getById(long id) {
        employeeValidator.existsById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.GET_BY_ID, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id);
        return (EmployeeDto) query.getSingleResult();
    }

    @Override
    public List<EmployeeDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(Employee.GET_ALL, Mapper.EMPLOYEE_DTO_MAPPER);
        return castResultListToListEmployeeDto(query.getResultList());
    }

    private List<EmployeeDto> castResultListToListEmployeeDto(List<?> resultList) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Object object : resultList) {
            if (object instanceof EmployeeDto) {
                employeeDtoList.add((EmployeeDto) object);
            }
        }
        return employeeDtoList;
    }

    @Override
    public EmployeeDto update(long id, EmployeeDto employeeDto, MultipartFile fileImage) {
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        EmployeeDto existingEmployeeDto = this.getById(id);
        employeeValidator.checkUpdate(employeeDto, existingEmployeeDto);
        if (!fileImage.isEmpty()) {
            UploadFile.deleteImage(this.getById(id).getImage());
            employeeDto.setImage(UploadFile.uploadImage(fileImage, EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.UPDATE, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        return (EmployeeDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        employeeValidator.existsById(id);
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        UploadFile.deleteImage(this.getById(id).getImage());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.DELETE)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id);
        query.execute();
    }
}
