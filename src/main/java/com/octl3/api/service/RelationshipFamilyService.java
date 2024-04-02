package com.octl3.api.service;

import com.octl3.api.dto.RelationshipFamilyDto;

import java.util.List;

public interface RelationshipFamilyService {
    RelationshipFamilyDto create(RelationshipFamilyDto relationshipFamilyDto);
    RelationshipFamilyDto getById(int id);
    List<RelationshipFamilyDto> getAll();
    RelationshipFamilyDto update(int id, RelationshipFamilyDto relationshipFamilyDto);
    void deleteById(int id);
}
