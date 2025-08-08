package ru.team24.controller.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.TemplatesController;

@RequestMapping("/api/v1/templates")
@RestController
@RequiredArgsConstructor
public class TemplateControllerImpl implements TemplatesController {
    private final TemplatesController templatesController;


    @Override
    public ResponseEntity<?> findTemplateById(long id) {

        return null;
    }
}
