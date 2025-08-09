package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.TemplatesController;
import ru.team24.service.interfaces.TemplateService;

@RequestMapping("/api/v1/templates")
@RestController
@RequiredArgsConstructor
public class TemplateControllerImpl implements TemplatesController {

    private final TemplateService templateService;

    @GetMapping("/{templateId}")
    @Override
    public ResponseEntity<?> findTemplateById(@PathVariable long templateId) {

        return null;
    }

    @PatchMapping("/{templatedId}")
    @Override
    public ResponseEntity<?> updateTemplateById(long templateId) {
        return null;
    }
}
