package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.StatutReservation;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    @ManyToOne
    private Client client;

    private Instant dateCreation;
    private Instant dateExpiration;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    private BigDecimal montantTotal;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<LigneReservation> lignes;

    @OneToMany(mappedBy = "reservation")
    private List<Paiement> paiements;
}