package ru.team24.service.domain.manager.observ.event;

import org.springframework.context.ApplicationEvent;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.domain.manager.observ.action.ActionRegisterNewCandidate;


public class NewCandidateEvent extends ApplicationEvent {

    public NewCandidateEvent(CandidateService source, ActionRegisterNewCandidate payload) {
        super(source);
    }
}
