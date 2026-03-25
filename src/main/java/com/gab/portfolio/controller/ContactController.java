package com.gab.portfolio.controller;

import com.gab.portfolio.dto.ContactRequest;
import com.gab.portfolio.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "https://portfolio-five-rho-64.vercel.app",
        "http://localhost:3000"
})
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> sendContact(
            @Valid @RequestBody ContactRequest request) {

        emailService.sendContactEmail(request);
        return ResponseEntity.ok(Map.of("message", "Email enviado com sucesso!"));
    }
}