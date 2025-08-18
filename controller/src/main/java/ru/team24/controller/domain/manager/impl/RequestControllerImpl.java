package ru.team24.controller.domain.manager.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.RequestController;
import ru.team24.service.dto.RequestDto;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/status")
    public ResponseEntity<?> getRequests(@RequestBody RequestStatusRequest statusRequest) {
        if (requestService.isRequestPending(statusRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page={page}")
    public ResponseEntity<Map<String, Object>> getRequestsPage(@PathVariable int page) {
        // Получаем страницу с 10 элементами, отсортированными по ID в обратном порядке
        Page<RequestDto> result = requestService.getRequestsPage(
                PageRequest.of(page, 10, Sort.by("requestId").ascending())
        );

        return ResponseEntity.ok(Map.of(
                "requests", result.getContent(),
                "pages", result.getTotalPages()
        ));
    }
}
