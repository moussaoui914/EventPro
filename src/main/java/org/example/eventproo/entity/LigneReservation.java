package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor
public class LigneReservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private CategorieBillet categorie;

    private int quantite;
    private BigDecimal prixUnitaireFixe;
    private BigDecimal sousTotal;
}