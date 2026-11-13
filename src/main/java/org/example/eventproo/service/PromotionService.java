package org.example.eventproo.service;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.PromotionCreateRequest;
import org.example.eventproo.entity.Evenement;
import org.example.eventproo.entity.Promotion;
import org.example.eventproo.enums.TypeReduction;
import org.example.eventproo.repository.EvenementRepository;
import org.example.eventproo.repository.PromotionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final EvenementRepository evenementRepository;

    @Transactional
    public Promotion creerPromotion(PromotionCreateRequest request) {
        Evenement evenement = evenementRepository.findById(request.getEvenementId())
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        Promotion promo = new Promotion();
        promo.setCode(request.getCode().toUpperCase());
        promo.setTypeReduction(request.getTypeReduction());
        promo.setValeurReduction(request.getValeurReduction());
        promo.setEvenement(evenement);

        return promotionRepository.save(promo);
    }

    public BigDecimal calculerReduction(String code, BigDecimal sousTotal) {
        Promotion promo = promotionRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Code promo invalide"));

        if (promo.getTypeReduction() == TypeReduction.PERCENTAGE) {
            return sousTotal.multiply(promo.getValeurReduction()).divide(BigDecimal.valueOf(100));
        } else {
            return promo.getValeurReduction().min(sousTotal);
        }
    }
}