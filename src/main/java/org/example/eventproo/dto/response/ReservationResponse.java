package org.example.eventproo.dto.response;

import lombok.*;
import org.example.eventproo.enums.StatutReservation;
import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationResponse {
    private String reference;
    private Instant dateExpiration;
    private StatutReservation statut;
    private BigDecimal montantTotal;
}