package org.example.eventproo.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventproo.dto.response.BilletResponse;
import org.example.eventproo.service.BilletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billets")
@RequiredArgsConstructor
public class BilletController {

    private final BilletService billetService;

    @PostMapping("/scanner")
    public BilletResponse scannerBillet(@RequestParam String code) {
        return billetService.controlerBillet(code);
    }
}