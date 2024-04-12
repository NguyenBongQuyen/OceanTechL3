package com.octl3.api.service;

import com.octl3.api.dto.ProfileEndDto;

import java.util.List;

public interface ProfileEndService {
    ProfileEndDto create(ProfileEndDto profileEndDto);
    ProfileEndDto getById(long id);
    List<ProfileEndDto> getAll();
    List<ProfileEndDto> getByStatus(String status);
    ProfileEndDto updateByManager(long id, ProfileEndDto profileEndDto);
    ProfileEndDto updateByLeader(long id, ProfileEndDto profileEndDto);
    void submit(long id, ProfileEndDto profileEndDto);
    void deleteById(long id);
}
