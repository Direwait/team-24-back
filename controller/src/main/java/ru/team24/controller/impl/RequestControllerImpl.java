package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.RequestController;
import ru.team24.service.interfaces.RequestService;

import java.util.List;

@RequestMapping("/api/v1/requests")
@RestController
@RequiredArgsConstructor
public class RequestControllerImpl implements RequestController {

    private final RequestService requestService;

    @GetMapping("/{requestId}")
    @Override
    public ResponseEntity<?> findByRequestId(@PathVariable long requestId) {

        return null;
    }

    @GetMapping("/{userId}")
    @Override
    public ResponseEntity<?> getByUserId(@PathVariable long userId) {

        return null;
    }

    @GetMapping("/{requestState}")
    @Override
    public ResponseEntity<List<?>> getByRequestState(@PathVariable Enum requestState) {

        return null;
    }

    @PatchMapping("/{requestId}")
    @Override
    public ResponseEntity<?> updateRequestByRequestId(@PathVariable long requestId) {
        return null;
    }

    @PostMapping()
    @Override
    public ResponseEntity<?> createRequest() {
        return null;
    }
}
