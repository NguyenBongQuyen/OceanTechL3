package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.SalaryIncrementDto;
import com.octl3.api.service.SalaryIncrementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.octl3.api.constants.MessageConst.DELETE_SUCCESS;
import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/salary-increments")
public class SalaryIncrementController {
    private final SalaryIncrementService salaryIncrementService;

    @PostMapping
    public DataResponse<SalaryIncrementDto> create(@Valid @RequestBody SalaryIncrementDto salaryIncrementDto) {
        return DataResponse.ok(salaryIncrementService.create(salaryIncrementDto));
    }

    @GetMapping
    public DataResponse<List<SalaryIncrementDto>> getAll() {
        return DataResponse.ok(salaryIncrementService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<SalaryIncrementDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(salaryIncrementService.getById(id));
    }

    @GetMapping("/by-status")
    public DataResponse<List<SalaryIncrementDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(salaryIncrementService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<SalaryIncrementDto> updateByManager(@PathVariable("id") long id, @RequestBody SalaryIncrementDto salaryIncrementDto) {
        return DataResponse.ok(salaryIncrementService.updateByManager(id, salaryIncrementDto));
    }

    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id, @RequestBody SalaryIncrementDto salaryIncrementDto) {
        salaryIncrementService.submit(id, salaryIncrementDto);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }

    @PutMapping("/by-leader/{id}")
    public DataResponse<SalaryIncrementDto> updateByLeader(@PathVariable("id") long id, @RequestBody SalaryIncrementDto salaryIncrementDto) {
        return DataResponse.ok(salaryIncrementService.updateByLeader(id, salaryIncrementDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        salaryIncrementService.deleteById(id);
        return DataResponse.ok(DELETE_SUCCESS);
    }

}
