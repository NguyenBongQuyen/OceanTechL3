package com.octl3.api.service;

import com.octl3.api.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    PromotionDto create(PromotionDto promotionDto);

    List<PromotionDto> getAll();

    PromotionDto getById(long id);

    List<PromotionDto> getByStatus(String status);

    PromotionDto updateByManager(long id, PromotionDto promotionDto);

    void submit(long id, PromotionDto promotionDto);

    PromotionDto updateByLeader(long id, PromotionDto promotionDto);

    void deleteById(long id);

}
