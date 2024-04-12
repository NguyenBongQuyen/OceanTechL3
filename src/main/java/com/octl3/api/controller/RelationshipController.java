package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.RelationshipDto;
import com.octl3.api.service.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/relationships")
public class RelationshipController {
    private final RelationshipService relationshipService;

    @PostMapping
    public DataResponse<RelationshipDto> create(@RequestBody RelationshipDto relationshipDto) {
        return DataResponse.ok(relationshipService.create(relationshipDto));
    }

    @GetMapping("/{id}")
    public DataResponse<RelationshipDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(relationshipService.getById(id));
    }

    @GetMapping
    public DataResponse<List<RelationshipDto>> getByEmployeeId(@RequestParam("employeeId") long employeeId) {
        return DataResponse.ok(relationshipService.getByEmployeeId(employeeId));
    }

    @PutMapping("/{id}")
    public DataResponse<RelationshipDto> update(@PathVariable("id") long id,
                                                @RequestBody RelationshipDto relationshipDto) {
        return DataResponse.ok(relationshipService.update(id, relationshipDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        relationshipService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
