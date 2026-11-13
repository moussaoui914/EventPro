package org.example.eventproo.service;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.PaiementRequest;
import org.example.eventproo.entity.*;
import org.example.eventproo.enums.StatutPaiement;
import org.example.eventproo.enums.StatutReservation;
import org.example.eventproo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public void payer(Long reservationId, PaiementRequest request) {
        if (paiementRepository.existsByIdempotencyKey(request.getIdempotencyKey())) {
            throw new RuntimeException("Paiement déjà effectué avec cette clé");
        }

        Reservation res = reservationRepository.findById(reservationId).orElseThrow();

        Paiement paiement = new Paiement();
        paiement.setReservation(res);
        paiement.setIdempotencyKey(request.getIdempotencyKey());
        paiement.setReference("PAY-" + UUID.randomUUID().toString().substring(0, 5));
        paiement.setMontant(res.getMontantTotal());
        paiement.setDatePaiement(Instant.now());
        paiement.setStatut(StatutPaiement.SUCCESS);
        paiementRepository.save(paiement);

        res.setStatut(StatutReservation.CONFIRMED);
        for (LigneReservation ligne : res.getLignes()) {
            CategorieBillet cat = ligne.getCategorie();
            cat.setPlacesReservees(cat.getPlacesReservees() - ligne.getQuantite());
            cat.setPlacesVendues(cat.getPlacesVendues() + ligne.getQuantite());
        }
        reservationRepository.save(res);
    }
}