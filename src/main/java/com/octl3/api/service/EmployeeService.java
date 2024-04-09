package com.octl3.api.service;

import com.octl3.api.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto, MultipartFile fileImage);
    EmployeeDto getById(int id);
    List<EmployeeDto> getAll();
    EmployeeDto update(int id, EmployeeDto employeeDto, MultipartFile file);
    void deleteById(int id);
}
