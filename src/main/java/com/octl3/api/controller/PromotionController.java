package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.PromotionDto;
import com.octl3.api.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.octl3.api.constants.MessageConst.DELETE_SUCCESS;
import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping
    public DataResponse<PromotionDto> create(@RequestBody PromotionDto promotionDto) {
        return DataResponse.ok(promotionService.create(promotionDto));
    }

    @GetMapping
    public DataResponse<List<PromotionDto>> getAll() {
        return DataResponse.ok(promotionService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<PromotionDto> getById(@PathVariable("id") long id) {
        return DataResponse.ok(promotionService.getById(id));
    }

    @GetMapping("/by-status")
    public DataResponse<List<PromotionDto>> getByStatus(@RequestParam("status") String status) {
        return DataResponse.ok(promotionService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public DataResponse<PromotionDto> updateByManager(@PathVariable("id") long id, @RequestBody PromotionDto promotionDto) {
        return DataResponse.ok(promotionService.updateByManager(id, promotionDto));
    }

    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id, @RequestBody PromotionDto promotionDto) {
        promotionService.submit(id, promotionDto);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }

    @PutMapping("/by-leader/{id}")
    public DataResponse<PromotionDto> updateByLeader(@PathVariable("id") long id, @RequestBody PromotionDto promotionDto) {
        return DataResponse.ok(promotionService.updateByLeader(id, promotionDto));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id) {
        promotionService.deleteById(id);
        return DataResponse.ok(DELETE_SUCCESS);
    }

}
