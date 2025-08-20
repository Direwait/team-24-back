package ru.team24.controller.domain.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.admin.TemplateController;
import ru.team24.service.dto.TemplateDto;
import ru.team24.service.domain.admin.TemplateService;

@RequestMapping("/api/v1/templates")
@RestController
@RequiredArgsConstructor
public class TemplateControllerImpl implements TemplateController {
    private final TemplateService templateService;

    @GetMapping("/recent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TemplateDto> findRecentTemplate() {
        var recentTemplate = templateService.findRecentTemplate();
        return ResponseEntity.ok(recentTemplate);
    }

    @GetMapping("/{templateId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findTemplateById(@PathVariable long templateId) {
        var templateById = templateService.findTemplateById(templateId);
        return new ResponseEntity<>(templateById, HttpStatus.OK);
    }

    @PatchMapping("/{templateId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateTemplateById(@PathVariable long templateId, @RequestBody TemplateDto templateDto) {
        templateService.updateTemplateById(templateId, templateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createTemplate(@RequestBody TemplateDto templateDto) {
        templateService.addTemplate(templateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteTemplate(@PathVariable long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteTemplateReal(@PathVariable long id) {
        templateService.deleteTemplateReal(id);
        return ResponseEntity.ok().build();
    }
}