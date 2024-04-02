package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.RelationshipFamilyDto;
import com.octl3.api.service.RelationshipFamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/relationships-family")
public class RelationshipFamilyController {
    private final RelationshipFamilyService relationshipFamilyService;

    @PostMapping
    public DataResponse<RelationshipFamilyDto> create(@RequestBody RelationshipFamilyDto relationshipFamilyDto) {
        return DataResponse.ok(relationshipFamilyService.create(relationshipFamilyDto));
    }

    @GetMapping("/{id}")
    public DataResponse<RelationshipFamilyDto> getById(@PathVariable("id") int id) {
        return DataResponse.ok(relationshipFamilyService.getById(id));
    }

    @GetMapping
    public DataResponse<List<RelationshipFamilyDto>> getAll() {
        return DataResponse.ok(relationshipFamilyService.getAll());
    }

    @PutMapping("/{id}")
    public DataResponse<RelationshipFamilyDto> update(@PathVariable("id") int id,
                                                      @RequestBody RelationshipFamilyDto relationshipFamilyDto) {
        return DataResponse.ok(relationshipFamilyService.update(id, relationshipFamilyDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        relationshipFamilyService.deleteById(id);
    }
}
