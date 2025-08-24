package ru.team24.service.observ.action;

import lombok.*;
import ru.team24.service.observ.ActionType;

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
