package ru.team24.action;

import lombok.*;

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
