package org.example.eventproo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.eventproo.enums.TypeReduction;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PromotionCreateRequest {
    @NotBlank
    private String code;

    @NotNull
    private TypeReduction typeReduction; // PERCENTAGE ou FIXED_AMOUNT

    @NotNull
    private BigDecimal valeurReduction;

    @NotNull
    private Long evenementId;
}