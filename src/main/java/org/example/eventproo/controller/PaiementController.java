package org.example.eventproo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.PaiementRequest;
import org.example.eventproo.service.PaiementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    @PostMapping("/{reservationId}")
    public String payer(@PathVariable Long reservationId, @Valid @RequestBody PaiementRequest request) {
        paiementService.payer(reservationId, request);
        return "Paiement effectué avec succès !";
    }
}