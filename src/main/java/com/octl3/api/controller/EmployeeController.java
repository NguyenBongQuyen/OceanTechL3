package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.dto.EmployeeProfileDto;
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
    public DataResponse<EmployeeDto> create(@RequestPart("employeeProfileDto") EmployeeProfileDto employeeProfileDto,
                                            @RequestPart("fileImage") MultipartFile fileImage) {
        return DataResponse.ok(employeeService.create(employeeProfileDto, fileImage));
    }

    @GetMapping("/{id}")
    public DataResponse<EmployeeDto> getById(@PathVariable("id") int id) {
        return DataResponse.ok(employeeService.getById(id));
    }

    @GetMapping
    public DataResponse<List<EmployeeDto>> getAll() {
        return DataResponse.ok(employeeService.getAll());
    }

    @PutMapping("/{id}")
    public DataResponse<EmployeeDto> update(@PathVariable("id") int id,
                                            @RequestBody EmployeeDto employeeDto,
                                            @RequestParam("file") MultipartFile file) {
        return DataResponse.ok(employeeService.update(id, employeeDto, file));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }
}
