package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.octl3.api.constants.MessageConst.DELETE_SUCCESS;
import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public DataResponse<RegistrationDto> create(@Valid @RequestBody RegistrationDto registrationDto) {
        return DataResponse.ok(registrationService.create(registrationDto));
    }

    @GetMapping
    public DataResponse<List<RegistrationDto>> getAll() {
        return DataResponse.ok(registrationService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<RegistrationDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(registrationService.getById(id));
    }

    @GetMapping("/by-status")
    public DataResponse<List<RegistrationDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(registrationService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<RegistrationDto> updateByManager(@PathVariable("id") long id,
                                                         @Valid @RequestBody RegistrationDto registrationDto) {
        return DataResponse.ok(registrationService.updateByManager(id, registrationDto));
    }

    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id,
                                       @RequestBody RegistrationDto registrationDto) {
        registrationService.submit(id, registrationDto);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }

    @PutMapping("/by-leader/{id}")
    public DataResponse<RegistrationDto> updateByLeader(@PathVariable("id") long id,
                                                        @RequestBody RegistrationDto registrationDto) {
        return DataResponse.ok(registrationService.updateByLeader(id, registrationDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        registrationService.deleteById(id);
        return DataResponse.ok(DELETE_SUCCESS);
    }

}
