package com.octl3.api.service;

import com.octl3.api.dto.RegistrationDto;

import java.util.List;

public interface RegistrationService {

    RegistrationDto create(RegistrationDto registrationDto);

    List<RegistrationDto> getAll();

    RegistrationDto getById(long id);

    List<RegistrationDto> getByStatus(String status);

    RegistrationDto updateByManager(long id, RegistrationDto registrationDto);

    void submit(long id, RegistrationDto registrationDto);

    RegistrationDto updateByLeader(long id, RegistrationDto registrationDto);

    void deleteById(long id);

}
