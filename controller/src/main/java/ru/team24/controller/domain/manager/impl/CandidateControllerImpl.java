package ru.team24.controller.domain.manager.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.CandidateController;
import ru.team24.service.dto.CandidateDto;
import ru.team24.service.domain.manager.CandidateService;

import java.util.List;


@RequestMapping("/api/v1/candidates")
@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/{candidateId}")
    public ResponseEntity<?> findCandidateId(@PathVariable long candidateId) {
        return new ResponseEntity<>(candidateService.findCandidateById(candidateId),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<?>> findAllCandidates() {
        return new ResponseEntity<>(candidateService.findAllCandidates(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addCandidateByMail(@RequestBody List<String> emails) {
        candidateService.addCandidateByMail(emails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
