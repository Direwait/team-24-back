package ru.team24.service.domain.general;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String subject, String body, String emailTo) throws MessagingException;
}
