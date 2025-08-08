package ru.team24.controller.future.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.future.GeneratorLinkController;

@RestController
@RequiredArgsConstructor
public class GeneratorLinkControllerImpl implements GeneratorLinkController {
    private final GeneratorLinkController generatorLinkController;

    @PostMapping("/generate")
    public ResponseEntity<String> generateLink() {
        return null;
    }
}
