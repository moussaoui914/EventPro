package org.example.eventproo.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.entity.*;
import org.example.eventproo.enums.StatutReservation;
import org.example.eventproo.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final ReservationRepository reservationRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void libererPlacesExpirees() {
        List<Reservation> toutes = reservationRepository.findAll();

        for (Reservation res : toutes) {
            if (res.getStatut() == StatutReservation.PENDING_PAYMENT
                    && res.getDateExpiration().isBefore(Instant.now())) {

                res.setStatut(StatutReservation.EXPIRED);

                for (LigneReservation ligne : res.getLignes()) {
                    CategorieBillet cat = ligne.getCategorie();
                    cat.setPlacesReservees(cat.getPlacesReservees() - ligne.getQuantite());
                }
                reservationRepository.save(res);
            }
        }
    }
}