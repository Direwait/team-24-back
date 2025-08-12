package ru.team24.service.interfaces;

import jakarta.mail.MessagingException;
import ru.team24.database.entities.Notification;
import ru.team24.database.entities.Request;

public interface EmailService {
    void sendEmail(String subject, String body, String emailTo) throws MessagingException;
}
