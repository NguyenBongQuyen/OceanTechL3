package com.octl3.api.service;

import com.octl3.api.dto.ProposalAdviceDto;

import java.util.List;

public interface ProposalAdviceService {

    ProposalAdviceDto create(ProposalAdviceDto proposalAdviceDto);

    List<ProposalAdviceDto> getAll();

    ProposalAdviceDto getById(long id);

    List<ProposalAdviceDto> getByStatus(String status);

    ProposalAdviceDto updateByManager(long id, ProposalAdviceDto proposalAdviceDto);

    void submit(long id, ProposalAdviceDto proposalAdviceDto);

    ProposalAdviceDto updateByLeader(long id, ProposalAdviceDto proposalAdviceDto);

    void deleteById(long id);

}
