package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.StatutPaiement;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;

    @Column(unique = true)
    private String idempotencyKey;

    private String reference;
    private BigDecimal montant;
    private Instant datePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @OneToMany(mappedBy = "paiement")
    private List<Remboursement> remboursements;
}