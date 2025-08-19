package ru.team24.controller.domain.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.security.UserDetailsImpl;


public interface SopdController {


    ResponseEntity<SopdDto> getRecent();

    ResponseEntity<SopdDto> updateSopds(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody String sopdText
    );

    ResponseEntity<Void> deleteSopds(
            @PathVariable long id
    );

    ResponseEntity<Void> deleteSopdReal(
            @PathVariable long id
    );
}