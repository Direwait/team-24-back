package ru.team24.controller.domain.manager.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.CandidateController;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.dto.candidate.EmailsDto;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Map;


@RequestMapping("/api/v1/candidates")
@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/{candidateId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> findCandidateId(@PathVariable long candidateId) {
        return new ResponseEntity<>(candidateService.findCandidateById(candidateId),HttpStatus.OK);
    }


    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<List<?>> findAllCandidates() {
        return new ResponseEntity<>(candidateService.findAllCandidates(),HttpStatus.OK);
    }

    //тут длинная цепочка менеджера, логика добавление кандидата в приложение,
    // комментарий можно удалить как и requestMapping  у каждого котроллера
    @PostMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
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
