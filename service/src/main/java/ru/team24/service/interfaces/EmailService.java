package ru.team24.service.interfaces;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String subject, String body, String emailTo) throws MessagingException;
}
