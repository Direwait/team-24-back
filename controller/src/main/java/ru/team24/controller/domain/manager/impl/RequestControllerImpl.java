package ru.team24.controller.domain.manager.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.manager.RequestController;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.dto.request.RequestWithCandidateAndManagerDto;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestCreationRequest;
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
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> findByRequestId(@PathVariable long requestId) {
        return new ResponseEntity<>(requestService.findByRequestId(requestId), HttpStatus.OK);
    }

    @DeleteMapping("/{requestId}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> hardDeleteByRequestId(@PathVariable long requestId) {
        requestService.hardDeleteByRequestId(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> hardDeleteAll() {
        requestService.hardDeleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<PagedModel<EntityModel<RequestWithCandidateDto>>> getRequests(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(required = false) String state,
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler<RequestWithCandidateDto> assembler) {

        Page<RequestWithCandidateDto> currentPage = requestService.findRequests(userDetails.getId(), state, pageable);
        PagedModel<EntityModel<RequestWithCandidateDto>> model = assembler.toModel(currentPage);
        
        return ResponseEntity.ok(model);
    }

    @GetMapping("/deleted")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<PagedModel<EntityModel<RequestWithCandidateAndManagerDto>>> getDeletedRequests(
            Pageable pageable,
            PagedResourcesAssembler<RequestWithCandidateAndManagerDto> assembler) {
        Page<RequestWithCandidateAndManagerDto> currentPage = requestService.findDeletedRequests(pageable);
        PagedModel<EntityModel<RequestWithCandidateAndManagerDto>> model = assembler.toModel(currentPage);
        return ResponseEntity.ok(model);
    }

    @PatchMapping()
    public ResponseEntity<?> updateRequest(@RequestBody CandidateResponse requestUpdate) {
        requestService.updateRequest(requestUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> createRequest(@AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RequestCreationRequest createRequest) throws JsonProcessingException {

        requestService.createRequests(createRequest, userDetails.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/status")
    public ResponseEntity<?> getRequests(@RequestBody RequestStatusRequest statusRequest) {
        if (requestService.isRequestPending(statusRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{requestId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> deleteRequest(@PathVariable long requestId) {
        requestService.softDeleteRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
