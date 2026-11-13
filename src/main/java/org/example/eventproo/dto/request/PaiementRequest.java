package org.example.eventproo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaiementRequest {
    @NotBlank
    private String idempotencyKey;
}