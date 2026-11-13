package org.example.eventproo.service;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.response.BilletResponse;
import org.example.eventproo.entity.Billet;
import org.example.eventproo.enums.StatutBillet;
import org.example.eventproo.repository.BilletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BilletService {

    private final BilletRepository billetRepository;

    @Transactional
    public BilletResponse controlerBillet(String code) {
        Billet billet = billetRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Billet introuvable"));

        if (billet.getStatut() == StatutBillet.USED) {
            throw new RuntimeException("Erreur 409 : Ce billet a déjà été scanné !");
        }

        billet.setStatut(StatutBillet.USED);
        billet.setDateControle(Instant.now());
        billetRepository.save(billet);

        return BilletResponse.builder()
                .code(billet.getCode())
                .statut(billet.getStatut())
                .message("Accès autorisé")
                .build();
    }
}