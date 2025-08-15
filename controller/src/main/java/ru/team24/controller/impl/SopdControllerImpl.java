package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.SopdController;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.interfaces.SopdService;

@RequestMapping("/api/v1/sopds")
@RestController
@RequiredArgsConstructor
public class SopdControllerImpl implements SopdController {

    private final SopdService sopdService;

    @GetMapping("/recent")
    @Override
    public ResponseEntity<SopdDto> getRecent() {
        var recentSopd = sopdService.findRecentSopd();
        return ResponseEntity.ok(recentSopd);
    }

    @PostMapping
    @Override
    public ResponseEntity<SopdDto> updateSopds(@RequestBody String sopdText) {
        long sopdId = sopdService.findRecentSopd().getSopdId();
        var sopdDto = SopdDto.builder().sopdText(sopdText).build();
        var sopdUpdated = sopdService.updateSopd(sopdId, sopdDto);
        return ResponseEntity.ok(sopdUpdated);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteSopds(@PathVariable long id) {
        sopdService.deleteSopd(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteSopdReal(@PathVariable long id) {
        sopdService.deleteSopdReal(id);
        return ResponseEntity.ok().build();
    }
}
