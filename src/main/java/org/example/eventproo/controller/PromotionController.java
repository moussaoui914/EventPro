package org.example.eventproo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.PromotionCreateRequest;
import org.example.eventproo.entity.Promotion;
import org.example.eventproo.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Promotion creer(@Valid @RequestBody PromotionCreateRequest request) {
        return promotionService.creerPromotion(request);
    }
}