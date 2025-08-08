package ru.team24.controller.impl;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.RequestController;

import java.util.List;

@RequestMapping("/api/v1/requests")
@RestController

public class RequestControllerImpl implements RequestController {


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
