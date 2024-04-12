package com.octl3.api.service;

import com.octl3.api.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto, MultipartFile fileImage);
    EmployeeDto getById(long id);
    List<EmployeeDto> getAll();
    EmployeeDto update(long id, EmployeeDto employeeDto, MultipartFile file);
    void deleteById(long id);
}
