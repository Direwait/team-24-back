package ru.team24.service.domain.mail;

import jakarta.mail.MessagingException;
import ru.team24.service.observ.action.ActionSendLetterCandidate;
import ru.team24.service.observ.action.ActionSendLetterToManager;

import java.io.IOException;


public interface EmailService {
    public void sendToManager(ActionSendLetterToManager action) throws MessagingException;
    void sendToCandidate(ActionSendLetterCandidate action) throws MessagingException, IOException;
}
