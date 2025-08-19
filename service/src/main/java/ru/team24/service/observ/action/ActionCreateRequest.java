package ru.team24.service.observ.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.team24.service.observ.ActionType;

@Getter
@Setter
@Builder
public class ActionCreateRequest {
    private long userId;
    private long candidateId;
    private String candidateMail;

    private ActionType actionType;
}
