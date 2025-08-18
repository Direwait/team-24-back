package ru.team24.service.observ.event;

import org.springframework.context.ApplicationEvent;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.observ.action.ActionCreateRequest;

public class EventSendToManager extends ApplicationEvent {

    public EventSendToManager(RequestService source, ActionCreateRequest payload) {
        super(source);
    }
}
