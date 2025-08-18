package ru.team24.service.observ.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.team24.service.observ.ActionType;

@Getter
@Setter
@Builder
public class ActionSendLetterCandidate {
    private String templateSubject;
    private String templateBody;

    private String candidateMail;
    private String token;

    private ActionType actionType;
}
