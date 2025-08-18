package ru.team24.service.domain.manager.observ.event;

import org.springframework.context.ApplicationEvent;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.domain.manager.observ.action.ActionRegisterNewCandidate;

public class SendToCandidateEvent extends ApplicationEvent {

    public SendToCandidateEvent(RequestService source, ActionRegisterNewCandidate payload) {
        super(source);
    }
}
