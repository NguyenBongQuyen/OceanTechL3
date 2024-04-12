package com.octl3.api.service;

import com.octl3.api.dto.RelationshipDto;

import java.util.List;

public interface RelationshipService {
    RelationshipDto create(RelationshipDto relationshipDto);
    RelationshipDto getById(long id);
    List<RelationshipDto> getByEmployeeId(long employeeId);
    RelationshipDto update(long id, RelationshipDto relationshipDto);
    void deleteById(long id);
}
