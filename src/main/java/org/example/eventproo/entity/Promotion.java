package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.eventproo.enums.TypeReduction;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor
public class Promotion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Evenement evenement;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private TypeReduction typeReduction;

    private BigDecimal valeurReduction;
}