package ru.team24.controller.impl;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.TemplatesController;

@RequestMapping("/api/v1/templates")
@RestController

public class TemplateControllerImpl implements TemplatesController {



    @Override
    public ResponseEntity<?> findTemplateById(long id) {

        return null;
    }
}
