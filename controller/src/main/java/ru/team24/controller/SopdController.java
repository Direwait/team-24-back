package ru.team24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.service.dto.SopdDto;


public interface SopdController {


    ResponseEntity<SopdDto> getRecent();

    ResponseEntity<SopdDto> updateSopds(@RequestBody String sopdText);

    ResponseEntity<Void> deleteSopds(@PathVariable long id);

    ResponseEntity<Void> deleteSopdReal(@PathVariable long id);
}