package ru.team24.service.domain.general;

import jakarta.mail.MessagingException;
import ru.team24.service.domain.manager.observ.action.ActionSendLetterCandidate;

public interface EmailService {
    void sendEmail(String subject, String body, String emailTo) throws MessagingException;
    void sendToCandidate(ActionSendLetterCandidate action) throws MessagingException;
}
