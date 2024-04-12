package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.ProfileEndDto;
import com.octl3.api.service.ProfileEndService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles-end")
public class ProfileEndController {
    private final ProfileEndService profileEndService;

    @PostMapping
    public DataResponse<ProfileEndDto> create(@RequestBody ProfileEndDto profileEndDto) {
        return DataResponse.ok(profileEndService.create(profileEndDto));
    }

    @GetMapping("/{id}")
    public DataResponse<ProfileEndDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(profileEndService.getById(id));
    }

    @GetMapping
    public DataResponse<List<ProfileEndDto>> getAll() {
        return DataResponse.ok(profileEndService.getAll());
    }

    @GetMapping("/status")
    public DataResponse<List<ProfileEndDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(profileEndService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<ProfileEndDto> updateByManager(@PathVariable("id") long id,
                                                       @RequestBody ProfileEndDto profileEndDto) {
        return DataResponse.ok(profileEndService.updateByManager(id, profileEndDto));
    }

    @PutMapping("/leader/{id}")
    public DataResponse<ProfileEndDto> updateByLeader(@PathVariable("id") long id,
                                                      @RequestBody ProfileEndDto profileEndDto) {
        return DataResponse.ok(profileEndService.updateByLeader(id, profileEndDto));
    }

    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id,
                                       @RequestBody ProfileEndDto profileEndDto) {
        profileEndService.submit(id, profileEndDto);
        return DataResponse.ok(MessageConst.SUBMIT_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        profileEndService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
