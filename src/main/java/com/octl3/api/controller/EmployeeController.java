package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public DataResponse<EmployeeDto> create(@RequestPart("employeeDto") EmployeeDto employeeDto,
                                            @RequestPart("fileImage") MultipartFile fileImage) {
        return DataResponse.ok(employeeService.create(employeeDto, fileImage));
    }

    @GetMapping("/{id}")
    public DataResponse<EmployeeDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(employeeService.getById(id));
    }

    @GetMapping
    public DataResponse<List<EmployeeDto>> getAll() {
        return DataResponse.ok(employeeService.getAll());
    }

    @PutMapping("/{id}")
    public DataResponse<EmployeeDto> update(@PathVariable("id") long id,
                                            @RequestPart("employeeDto") EmployeeDto employeeDto,
                                            @RequestPart("fileImage") MultipartFile fileImage) {
        return DataResponse.ok(employeeService.update(id, employeeDto, fileImage));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        employeeService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
