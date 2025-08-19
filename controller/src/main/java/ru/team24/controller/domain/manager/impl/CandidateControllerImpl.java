package ru.team24.controller.domain.manager.impl;

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

    @RequestMapping("/v1/candidates")
    @GetMapping("/{candidateId}")
    public ResponseEntity<?> findCandidateId(@PathVariable long candidateId) {
        return new ResponseEntity<>(candidateService.findCandidateById(candidateId),HttpStatus.OK);
    }

    // это используется ?
    @RequestMapping("/v1/candidates")
    @GetMapping()
    public ResponseEntity<List<?>> findAllCandidates() {
        return new ResponseEntity<>(candidateService.findAllCandidates(),HttpStatus.OK);
    }

    @RequestMapping("/v1/candidates")
    @Deprecated //тут передаются как [mail, mail, mail]
    @PostMapping()
    public ResponseEntity<?> addCandidateByMail(@RequestBody List<String> emails) {
        candidateService.addCandidateByMail(emails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //тут длинная цепочка менеджера, логика добавление кандидата в приложение,
    // комментарий можно удалить как и requestMapping  у каждого котроллера
    @RequestMapping("/v2/candidates")
    @PostMapping()
    public ResponseEntity<Map<String, String>> link(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EmailsDto emails
    )
    {
        var managerId = userDetails.getId();
        candidateService.addCandidateByMail(emails.getEmails(), managerId);
        return ResponseEntity.ok().build();
    }
}
