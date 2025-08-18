package ru.team24.controller.domain.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Override
    public ResponseEntity<TemplateDto> findRecentTemplate() {
        var recentTemplate = templateService.findRecentTemplate();
        return ResponseEntity.ok(recentTemplate);
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<?> findTemplateById(@PathVariable long templateId) {
        var templateById = templateService.findTemplateById(templateId);
        return new ResponseEntity<>(templateById, HttpStatus.OK);
    }

    @PatchMapping("/{templateId}")
    public ResponseEntity<?> updateTemplateById(@PathVariable long templateId, @RequestBody TemplateDto templateDto) {
        templateService.updateTemplateById(templateId, templateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createTemplate(@RequestBody TemplateDto templateDto) {
        templateService.addTemplate(templateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteTemplate(@PathVariable long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteTemplateReal(@PathVariable long id) {
        templateService.deleteTemplateReal(id);
        return ResponseEntity.ok().build();
    }
}