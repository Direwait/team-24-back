package ru.team24.service.observ.action;

import lombok.*;
import ru.team24.service.observ.ActionType;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionSendLetterToManager implements Serializable {
    private String candidateMail;
    private String managerMail;

    private String candidateFirstName;
    private String candidateLastName;
    private String requestState;

    private ActionType actionType;
}
