package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.CandidateController;
import ru.team24.service.interfaces.CandidateService;

import java.util.List;


@RequestMapping("/api/v1/candidates")
@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {

    private final CandidateService candidateService;

    @GetMapping("/{candidateId}")
    @Override
    public ResponseEntity<?> findCandidateId(@PathVariable long candidateId) {

        return null;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<?>> findAllCandidates() {
        return null;
    }

    @PostMapping()
    @Override
    public ResponseEntity<?> addCandidate() {
        return null;
    }
}
