package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public DataResponse<RegistrationDto> create(@RequestBody RegistrationDto registrationDto) {
        return DataResponse.ok(registrationService.create(registrationDto));
    }

    @GetMapping
    public DataResponse<List<RegistrationDto>> getAll() {
        return DataResponse.ok(registrationService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<RegistrationDto> getById(@PathVariable("id") Long id) {
        return DataResponse.ok(registrationService.getById(id));
    }

    @GetMapping("/get-by-status")
    public DataResponse<List<RegistrationDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(registrationService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<RegistrationDto> updateByManager(@PathVariable("id") Long id, @RequestBody RegistrationDto registrationDto) {
        return DataResponse.ok(registrationService.updateByManager(id, registrationDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        registrationService.deleteById(id);
    }

}
