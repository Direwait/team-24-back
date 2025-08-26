package ru.team24.controller.domain.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.admin.SopdController;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.domain.admin.SopdService;
import ru.team24.service.payload.request.SopdUpdateRequest;
import ru.team24.service.security.UserDetailsImpl;

@RequestMapping("/api/v1/sopds")
@RestController
@RequiredArgsConstructor
public class SopdControllerImpl implements SopdController {

    private final SopdService sopdService;

    @GetMapping("/recent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SopdDto> getRecent() {
        var recentSopd = sopdService.findRecentSopd();
        return ResponseEntity.ok(recentSopd);
    }

    @GetMapping("/forCandidate")
    public ResponseEntity<SopdDto> getForCandidate() {
        var recentSopd = sopdService.findRecentSopd();
        return ResponseEntity.ok(recentSopd);
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SopdDto> updateSopds(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody SopdUpdateRequest sopdUpdateRequest
    ) {
        long sopdId = sopdService.findRecentSopd().getSopdId();
        var sopdDto = SopdDto.builder().sopdText(sopdUpdateRequest.getSopdText()).build();
        var sopdUpdated = sopdService.updateSopd(sopdId, sopdDto);
        return ResponseEntity.ok(sopdUpdated);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSopds(@PathVariable long id) {
        sopdService.deleteSopd(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSopdReal(@PathVariable long id) {
        sopdService.deleteSopdReal(id);
        return ResponseEntity.ok().build();
    }
}
