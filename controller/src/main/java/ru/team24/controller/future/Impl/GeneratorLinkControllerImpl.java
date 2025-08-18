package ru.team24.controller.future.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.future.GeneratorLinkController;
import ru.team24.controller.future.dto.EmailsDto;
import ru.team24.service.domain.admin.SopdService;
import ru.team24.service.domain.admin.TemplateService;
import ru.team24.service.dto.CandidateDto;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Map;
@Slf4j
@RequestMapping("/api/v1/link")
@RestController
@RequiredArgsConstructor
public class GeneratorLinkControllerImpl implements GeneratorLinkController {

    private final TokenManager linkGen;
    private final CandidateService candidateService;



    @PostMapping("/gene")
    public ResponseEntity<Map<String, String>> link(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EmailsDto emails
    )
    {
        var managerId = userDetails.getId();

        log.info("заходим на почты {}",emails.getEmails().get(0));
        candidateService.addCandidateByMail(emails.getEmails(), managerId);

        return ResponseEntity.ok().build();
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
