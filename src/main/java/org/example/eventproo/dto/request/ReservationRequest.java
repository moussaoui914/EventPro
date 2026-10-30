package org.example.eventproo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservationRequest {
    @NotNull
    private Long categorieId;

    @Min(1)
    private int quantite;

    private String codePromo;
}