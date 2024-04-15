package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.ProposalAdviceDto;
import com.octl3.api.service.ProposalAdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.octl3.api.constants.MessageConst.DELETE_SUCCESS;
import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proposal-advices")
public class ProposalAdviceController {

    private final ProposalAdviceService proposalAdviceService;

    @PostMapping
    public DataResponse<ProposalAdviceDto> create(@Valid @RequestBody ProposalAdviceDto proposalAdviceDto) {
        return DataResponse.ok(proposalAdviceService.create(proposalAdviceDto));
    }

    @GetMapping
    public DataResponse<List<ProposalAdviceDto>> getAll() {
        return DataResponse.ok(proposalAdviceService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<ProposalAdviceDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(proposalAdviceService.getById(id));
    }

    @GetMapping("/by-status")
    public DataResponse<List<ProposalAdviceDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(proposalAdviceService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<ProposalAdviceDto> updateByManager(@PathVariable("id") long id,
                                                           @Valid @RequestBody ProposalAdviceDto proposalAdviceDto) {
        return DataResponse.ok(proposalAdviceService.updateByManager(id, proposalAdviceDto));
    }

    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id,
                                       @RequestBody ProposalAdviceDto proposalAdviceDto) {
        proposalAdviceService.submit(id, proposalAdviceDto);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }

    @PutMapping("/by-leader/{id}")
    public DataResponse<ProposalAdviceDto> updateByLeader(@PathVariable("id") long id,
                                                          @RequestBody ProposalAdviceDto proposalAdviceDto) {
        return DataResponse.ok(proposalAdviceService.updateByLeader(id, proposalAdviceDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        proposalAdviceService.deleteById(id);
        return DataResponse.ok(DELETE_SUCCESS);
    }

}
