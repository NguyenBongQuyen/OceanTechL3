package com.octl3.api.service;

import com.octl3.api.dto.SalaryIncrementDto;

import java.util.List;

public interface SalaryIncrementService {

    SalaryIncrementDto create(SalaryIncrementDto salaryIncrementDto);

    List<SalaryIncrementDto> getAll();

    SalaryIncrementDto getById(long id);

    List<SalaryIncrementDto> getByStatus(String status);

    SalaryIncrementDto updateByManager(long id, SalaryIncrementDto salaryIncrementDto);

    void submit(long id, SalaryIncrementDto salaryIncrementDto);

    SalaryIncrementDto updateByLeader(long id, SalaryIncrementDto salaryIncrementDto);

    void deleteById(long id);

}
