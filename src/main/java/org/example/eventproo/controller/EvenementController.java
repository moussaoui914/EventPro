package org.example.eventproo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.request.EvenementCreateRequest;
import org.example.eventproo.entity.Evenement;
import org.example.eventproo.service.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evenements")
@RequiredArgsConstructor
public class EvenementController {

    private final EvenementService evenementService;

    @GetMapping
    public List<Evenement> listerTous() {
        return evenementService.listerTous();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evenement creer(@Valid @RequestBody EvenementCreateRequest request) {
        return evenementService.creerEvenement(request);
    }
}