package ru.team24.controller.domain.manager.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.RequestController;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;
import ru.team24.service.security.UserDetailsImpl;

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

    @Deprecated
    @Override
    public ResponseEntity<?> getByUserId(long id) {
        return null;
    }

    @Deprecated
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<RequestWithCandidateDto>> getByUserId(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort) {

        return ResponseEntity.ok(requestService.getByUserId(userId));
    }

    @Deprecated
    @GetMapping("/getByState/{requestState}")
    public ResponseEntity<List<RequestWithCandidateDto>> getByRequestState(
            @PathVariable String requestState) {
        return ResponseEntity.ok(requestService.getByRequestState(requestState));
    }

    // todo
    // перепроверить новую палидацию
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<RequestWithCandidateDto>>> getRequests(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(required = false) String state,
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler<RequestWithCandidateDto> assembler) {

        Page<RequestWithCandidateDto> page = requestService.findRequests(userDetails.getId(), state, pageable);
        PagedModel<EntityModel<RequestWithCandidateDto>> model = assembler.toModel(page);
        
        return ResponseEntity.ok(model);
    }

    // todo
    //это надо?
    public ResponseEntity<?> updateRequestByRequestId(long requestId, RequestDto requestDto) {
        return null;
    }

    @PatchMapping()
    public ResponseEntity<?> updateRequest(@RequestBody CandidateResponse requestUpdate) {
        requestService.updateRequest(requestUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //
    @GetMapping("/status")
    public ResponseEntity<?> getRequests(@RequestBody RequestStatusRequest statusRequest) {
        if (requestService.isRequestPending(statusRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
