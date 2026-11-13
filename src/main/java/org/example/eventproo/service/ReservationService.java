package org.example.eventproo.service;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.ReservationRequest;
import org.example.eventproo.dto.response.ReservationResponse;
import org.example.eventproo.entity.*;
import org.example.eventproo.enums.StatutReservation;
import org.example.eventproo.exception.AucuneReservationTrouveeException;
import org.example.eventproo.mapper.ReservationMapper;
import org.example.eventproo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CategorieBilletRepository categorieRepository;
    private final ClientRepository clientRepository;
    private final PromotionService promotionService;
    private final ReservationMapper reservationMapper;

    @Transactional
    public ReservationResponse reserver(ReservationRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        CategorieBillet cat = categorieRepository.findByIdWithLock(request.getCategorieId());

        if (!cat.verifierStock(request.getQuantite())) {
            throw new RuntimeException("Stock insuffisant");
        }

        cat.setPlacesReservees(cat.getPlacesReservees() + request.getQuantite());

        Reservation res = new Reservation();
        res.setReference("RES-" + UUID.randomUUID().toString().substring(0, 5));
        res.setDateCreation(Instant.now());
        res.setDateExpiration(Instant.now().plus(10, ChronoUnit.MINUTES));
        res.setStatut(StatutReservation.PENDING_PAYMENT);
        res.setClient(client);

        BigDecimal sousTotal = cat.getPrixUnitaire().multiply(BigDecimal.valueOf(request.getQuantite()));

        BigDecimal remise = BigDecimal.ZERO;
        if (request.getCodePromo() != null && !request.getCodePromo().isEmpty()) {
            remise = promotionService.calculerReduction(request.getCodePromo(), sousTotal);
        }

        BigDecimal totalFinal = sousTotal.subtract(remise).max(BigDecimal.ZERO);
        res.setMontantTotal(totalFinal);

        LigneReservation ligne = new LigneReservation();
        ligne.setQuantite(request.getQuantite());
        ligne.setCategorie(cat);
        ligne.setPrixUnitaireFixe(cat.getPrixUnitaire());
        ligne.setSousTotal(sousTotal);
        ligne.setReservation(res);

        res.setLignes(new ArrayList<>());
        res.getLignes().add(ligne);

        reservationRepository.save(res);

        return ReservationResponse.builder()
                .reference(res.getReference())
                .dateExpiration(res.getDateExpiration())
                .statut(res.getStatut())
                .montantTotal(res.getMontantTotal())
                .build();
    }



    // MISE EN SITUATION

    public List<ReservationResponse> rechercherReservation(String email, String nomCategorie, int QuantiteMin, String titreEvenement){
        List<Reservation> reservations = reservationRepository.findReservationsConfirmees(email,nomCategorie,QuantiteMin,titreEvenement);

        if (reservations.isEmpty()){
            throw  new AucuneReservationTrouveeException("Reservation non trouver");
        }

        return reservations.stream().map(reservationMapper::toResponse).collect(Collectors.toList());
    }
}