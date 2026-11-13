package org.example.eventproo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.StatutEvenement;
import java.time.Instant;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
public class Evenement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Organisateur organisateur;

    private String titre;
    private String lieu;
    private Instant dateDebut;

    @Enumerated(EnumType.STRING)
    private StatutEvenement statut;

    private int capacite;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<CategorieBillet> categories;
}