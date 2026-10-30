package org.example.eventproo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EvenementCreateRequest {

    @NotBlank
    private String titre;

    private String lieu;

    @NotNull
    private Instant dateDebut;

    @NotNull
    private int capacite;

    // On inclut les catégories directement dans la création de l'événement
    private List<CategorieRequest> categories;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class CategorieRequest {
        private String nom;
        private BigDecimal prixUnitaire;
        private int quantiteTotale;
    }
}