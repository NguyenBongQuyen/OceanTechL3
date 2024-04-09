package com.octl3.api.service;

import com.octl3.api.dto.ProfileEndDto;

import java.util.List;

public interface ProfileEndService {
    ProfileEndDto create(ProfileEndDto profileEndDto);
    ProfileEndDto getById(int id);
    List<ProfileEndDto> getAll();
    List<ProfileEndDto> getByStatus(String status);
    ProfileEndDto updateByManager(int id, ProfileEndDto profileEndDto);
    ProfileEndDto updateByLeader(int id, ProfileEndDto profileEndDto);
    void submit(int id, ProfileEndDto profileEndDto);
    void deleteById(int id);
}
