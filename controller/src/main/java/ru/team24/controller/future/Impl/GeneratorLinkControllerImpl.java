package ru.team24.controller.future.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.future.GeneratorLinkController;
import ru.team24.database.entities.Candidate;
import ru.team24.service.linkInvite.LinkGeneratorService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/link")
@RestController
@RequiredArgsConstructor
public class GeneratorLinkControllerImpl implements GeneratorLinkController {

    private final LinkGeneratorService linkGeneratorService;

    @PostMapping("/generate")
    @Override
    public ResponseEntity<Map<String, String>> generateLink(
            @RequestParam Long candidateId,
            @RequestParam Long templateId,
            @RequestParam Long userId) {

        String token = linkGeneratorService.createRequest(candidateId, templateId, userId);
        String link = "http://localhost:8989/api/v1/link/update-form?token=" + token;

        Map<String, String> response = new HashMap<>();
        response.put("link", link);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-form")
    @Override
    public ResponseEntity<String> updateCandidate(
            @RequestParam String token,
            @RequestBody Candidate candidateData) {

        if (!linkGeneratorService.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Неверный или просроченный токен");
        }

        linkGeneratorService.updateCandidateData(token, candidateData);
        return ResponseEntity.ok("Данные кандидата обновлены");
    }

}
