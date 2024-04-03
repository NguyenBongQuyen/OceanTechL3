package com.octl3.api.service;

import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.dto.EmployeeProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeProfileDto employeeProfileDto, MultipartFile fileImage);
    EmployeeDto getById(int id);
    List<EmployeeDto> getAll();
    EmployeeDto update(int id, EmployeeDto employeeDto, MultipartFile file);
    void deleteById(int id);
}
