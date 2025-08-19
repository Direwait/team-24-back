package ru.team24.controller.domain.manager.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.CandidateController;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.dto.candidate.EmailsDto;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Map;


@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/v1/candidates/{candidateId}")
    public ResponseEntity<?> findCandidateId(@PathVariable long candidateId) {
        return new ResponseEntity<>(candidateService.findCandidateById(candidateId),HttpStatus.OK);
    }

    @GetMapping("/v1/candidates")
    public ResponseEntity<List<?>> findAllCandidates() {
        return new ResponseEntity<>(candidateService.findAllCandidates(),HttpStatus.OK);
    }

    @PostMapping("/v1/candidates")
    public ResponseEntity<?> addCandidateByMail(@RequestBody List<String> emails) {
        candidateService.addCandidateByMail(emails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/v2/candidates")
    public ResponseEntity<Map<String, String>> link(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EmailsDto emails
    )
    {
        var managerId = userDetails.getId();
        candidateService.addCandidateByMail(emails.getEmails().stream().toList(), managerId);
        return ResponseEntity.ok().build();
    }
}
