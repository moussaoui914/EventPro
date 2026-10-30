package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.StatutRemboursement;
import java.math.BigDecimal;
import java.time.Instant;

@Entity @Getter @Setter @NoArgsConstructor
public class Remboursement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paiement paiement;

    private BigDecimal montant;
    private String motif;
    private Instant dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutRemboursement statut;
}