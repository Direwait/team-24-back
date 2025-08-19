package ru.team24.service.payload.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RequestCreationRequest {
    private List<String> emails;
}
