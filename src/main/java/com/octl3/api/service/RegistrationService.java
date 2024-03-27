package com.octl3.api.service;

import com.octl3.api.dto.RegistrationDto;

import java.util.List;

public interface RegistrationService {

    RegistrationDto create(RegistrationDto registrationDto);

    List<RegistrationDto> getAll();

    RegistrationDto getById(Long id);

    List<RegistrationDto> getByStatus(String status);

    RegistrationDto updateByManager(Long id, RegistrationDto registrationDto);

    RegistrationDto updateByLeader(Long id, RegistrationDto registrationDto);

    void deleteById(Long id);

}
