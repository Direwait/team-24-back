package ru.team24.controller.future.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.future.GeneratorLinkController;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.dto.CandidateDto;
import ru.team24.service.domain.manager.tokenLinkManager.tokenManager;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.security.UserDetailsImpl;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/link")
@RestController
@RequiredArgsConstructor
public class GeneratorLinkControllerImpl implements GeneratorLinkController {

    private final tokenManager linkGen;
    private final CandidateService candidateService;
    private final RequestService requestService;

    // todo
    // requestWithID
    // long candidateId
    // long templateId = default recent
    // long sopdId = default recent

    //вынести в request controller
    @PostMapping("/generate")
    @Override
    public ResponseEntity<Map<String, String>> generateLink(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam long candidateId,
            @RequestParam long templateId,
            @RequestParam long sopdId)
    {
        String token = linkGen.generateAccessToken();

        requestService.createRequestWithToken(candidateId, templateId, userDetails.getId(), sopdId, token);

        String link = "http://localhost:5173/api/v1/link/update-form?token=" + token;

        Map<String, String> response = new HashMap<>();
        response.put("link", link);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    //вынести в candidate controller
    //без id
    @PostMapping("/update-form")
    @Override
    public ResponseEntity<String> updateCandidate(
            @RequestParam String token,
            @RequestBody CandidateDto candidateData) {

        if (!linkGen.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Неверный или просроченный токен");
        }

        //обновить кандидата с данными по токену (ключу)
        candidateService.updateCandidateData(token, candidateData);
        return ResponseEntity.ok("Данные кандидата обновлены");
    }

}
