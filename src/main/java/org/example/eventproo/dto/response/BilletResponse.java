package org.example.eventproo.dto.response;

import lombok.*;
import org.example.eventproo.enums.StatutBillet;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BilletResponse {
    private String code;
    private StatutBillet statut;
    private String message;
}