package com.octl3.api.service.impl;

import com.octl3.api.constants.StoredProcedure.Employee;
import com.octl3.api.constants.StoredProcedure.Mapper;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.service.EmployeeService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.utils.UploadFile;
import lombok.RequiredArgsConstructor;
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

    @Override
    public EmployeeDto create(EmployeeDto employeeDto, MultipartFile fileImage) {
        if (!fileImage.isEmpty()) {
            employeeDto.setImage(UploadFile.uploadImage(fileImage, EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.CREATE, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        query.execute();
        return employeeDto;
    }

    @Override
    public EmployeeDto getById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.GET_BY_ID, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Integer.class, ParameterMode.IN)
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
    public EmployeeDto update(int id, EmployeeDto employeeDto, MultipartFile fileImage) {
        if (!fileImage.isEmpty()) {
            UploadFile.deleteImage(getById(id).getImage());
            employeeDto.setImage(UploadFile.uploadImage(fileImage, EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.UPDATE, Mapper.EMPLOYEE_DTO_MAPPER)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_JSON, String.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        query.execute();
        return employeeDto;
    }

    @Override
    public void deleteById(int id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Employee.DELETE)
                        .registerStoredProcedureParameter(Parameter.EMPLOYEE_ID_PARAM, Integer.class, ParameterMode.IN)
                        .setParameter(Parameter.EMPLOYEE_ID_PARAM, id);
        query.execute();
    }
}
