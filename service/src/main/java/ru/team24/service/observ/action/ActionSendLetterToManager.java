package ru.team24.service.observ.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.team24.service.observ.ActionType;

@Builder
@Getter
@Setter
public class ActionSendLetterToManager {
    private String candidateMail;
    private String managerMail;

    private String candidateFirstName;
    private String candidateLastName;
    private String requestState;

    private ActionType actionType;
}
