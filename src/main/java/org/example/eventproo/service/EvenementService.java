package org.example.eventproo.service;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.EvenementCreateRequest;
import org.example.eventproo.entity.CategorieBillet;
import org.example.eventproo.entity.Evenement;
import org.example.eventproo.enums.StatutEvenement;
import org.example.eventproo.repository.EvenementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvenementService {

    private final EvenementRepository evenementRepository;

    public List<Evenement> listerTous() {
        return evenementRepository.findAll();
    }

    @Transactional
    public Evenement creerEvenement(EvenementCreateRequest request) {
        Evenement evenement = new Evenement();
        evenement.setTitre(request.getTitre());
        evenement.setLieu(request.getLieu());
        evenement.setDateDebut(request.getDateDebut());
        evenement.setCapacite(request.getCapacite());
        evenement.setStatut(StatutEvenement.DRAFT);

        if (request.getCategories() != null) {
            List<CategorieBillet> categories = new ArrayList<>();
            for (var catReq : request.getCategories()) {
                CategorieBillet cat = new CategorieBillet();
                cat.setNom(catReq.getNom());
                cat.setPrixUnitaire(catReq.getPrixUnitaire());
                cat.setQuantiteTotale(catReq.getQuantiteTotale());
                cat.setEvenement(evenement);
                categories.add(cat);
            }
            evenement.setCategories(categories);
        }

        return evenementRepository.save(evenement);
    }
}