package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.RequestController;

import java.util.List;

@RequestMapping("/api/v1/requests")
@RestController
@RequiredArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestController requestController;

    @Override
    public ResponseEntity<?> findByRequestId(long id) {

        return null;
    }

    @Override
    public ResponseEntity<?> getByUserId(long id) {

        return null;
    }

    @Override
    public ResponseEntity<List<?>> getByRequestState(Enum state) {

        return null;
    }
}
