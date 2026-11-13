package org.example.eventproo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor
public class CategorieBillet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Evenement evenement;

    private String nom;
    private BigDecimal prixUnitaire;
    private int quantiteTotale;

    private int placesReservees = 0;
    private int placesVendues = 0;

    public boolean verifierStock(int quantite) {
        return (quantiteTotale - (placesReservees + placesVendues)) >= quantite;
    }
}