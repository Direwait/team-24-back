package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.CandidateController;

import java.util.List;


@RequestMapping("/api/v1/candidates")
@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {
    private final CandidateController candidateController;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findCandidateId(long id) {

        return null;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<?>> findAllCandidates() {
        return null;
    }
}
