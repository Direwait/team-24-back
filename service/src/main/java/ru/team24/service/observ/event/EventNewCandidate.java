package ru.team24.service.observ.event;

import org.springframework.context.ApplicationEvent;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.observ.action.ActionCreateRequest;


public class EventNewCandidate extends ApplicationEvent {

    public EventNewCandidate(CandidateService source, ActionCreateRequest payload) {
        super(source);
    }
}
