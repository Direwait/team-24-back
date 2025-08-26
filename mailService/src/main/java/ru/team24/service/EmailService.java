package ru.team24.service;


import jakarta.mail.MessagingException;
import ru.team24.action.ActionSendLetterCandidate;
import ru.team24.action.ActionSendLetterToManager;

public interface EmailService {

    public void sendToManager(ActionSendLetterToManager action) throws MessagingException;
    void sendToCandidate(ActionSendLetterCandidate action) throws MessagingException;
}
