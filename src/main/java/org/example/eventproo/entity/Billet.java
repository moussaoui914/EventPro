package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.StatutBillet;
import java.time.Instant;

@Entity @Getter @Setter @NoArgsConstructor
public class Billet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    private LigneReservation ligneReservation;

    private Instant dateEmission;
    private Instant dateControle;

    @Enumerated(EnumType.STRING)
    private StatutBillet statut;
}