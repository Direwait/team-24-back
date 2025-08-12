package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.RequestController;
import ru.team24.service.dto.RequestDto;
import ru.team24.service.interfaces.RequestService;

import java.util.List;

@RequestMapping("/api/v1/requests")
@RestController
@RequiredArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestService requestService;

    @GetMapping("/{requestId}")
    public ResponseEntity<?> findByRequestId(@PathVariable long requestId) {
        return new ResponseEntity<>(requestService.findByRequestId(requestId), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(requestService.getByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{requestState}")
    public ResponseEntity<List<?>> getByRequestState(@PathVariable String requestState) {
        return new ResponseEntity<>(requestService.getByRequestState(requestState), HttpStatus.OK);
    }

    @PatchMapping("/{requestId}")
    public ResponseEntity<?> updateRequestByRequestId(@PathVariable long requestId, @RequestBody RequestDto requestDto) {
        requestService.updateRequestByRequestId(requestId, requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<?> createRequest(@RequestBody RequestDto requestDto) {
        requestService.createRequest(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
