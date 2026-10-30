package org.example.eventproo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.ReservationRequest;
import org.example.eventproo.dto.response.ReservationResponse;
import org.example.eventproo.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponse reserver(@Valid @RequestBody ReservationRequest request) {
        return reservationService.reserver(request);
    }
}