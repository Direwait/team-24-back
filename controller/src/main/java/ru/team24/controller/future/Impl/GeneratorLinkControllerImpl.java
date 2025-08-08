package ru.team24.controller.future.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.future.GeneratorLinkController;

@RestController
public class GeneratorLinkControllerImpl implements GeneratorLinkController {

    @PostMapping("/generate")
    public ResponseEntity<String> generateLink() {
        return null;
    }
}
