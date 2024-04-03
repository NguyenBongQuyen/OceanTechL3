package com.octl3.api.service;

import com.octl3.api.dto.RelationshipDto;

import java.util.List;

public interface RelationshipService {
    RelationshipDto create(RelationshipDto relationshipDto);
    RelationshipDto getById(int id);
    List<RelationshipDto> getByEmployeeId(int employeeId);
    RelationshipDto update(int id, RelationshipDto relationshipDto);
    void deleteById(int id);
}
