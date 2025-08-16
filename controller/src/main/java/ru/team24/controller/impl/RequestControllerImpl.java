package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.RequestController;
import ru.team24.service.dto.RequestDto;
import ru.team24.service.interfaces.RequestService;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;

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

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(requestService.getByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getByState/{requestState}")
    public ResponseEntity<List<?>> getByRequestState(@PathVariable String requestState) {
        return new ResponseEntity<>(requestService.getByRequestState(requestState), HttpStatus.OK);
    }

    public ResponseEntity<?> updateRequestByRequestId(long requestId, RequestDto requestDto) {
        return null;
    }

    @PatchMapping()
    public ResponseEntity<?> updateRequest(@RequestBody RequestUpdateRequest requestUpdate) {
        requestService.updateRequest(requestUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createRequest(@RequestBody RequestDto requestDto) {
        requestService.createRequest(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/status")
    public ResponseEntity<?> getRequests(@RequestBody RequestStatusRequest statusRequest) {
        if (requestService.isRequestPending(statusRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
