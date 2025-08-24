package ru.team24.action;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionSendLetterCandidate implements Serializable {
    private String templateSubject;
    private String templateBody;

    private String candidateMail;
    private String token;

    private ActionType actionType;
}
