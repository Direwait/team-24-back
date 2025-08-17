package ru.team24.service.domain.manager.observ.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.team24.service.domain.manager.observ.ActionType;

@Getter
@Setter
@Builder
public class ActionRegisterNewCandidate {
    private long userId;
    private long candidateId;
    private String candidateMail;

    private ActionType actionType;
}
