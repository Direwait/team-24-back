package ru.team24.service.domain.general.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.service.domain.general.EmailService;
import ru.team24.service.domain.manager.observ.action.ActionSendLetterCandidate;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${app.link.url}")
    private String inviteLink;

    private final JavaMailSender mailSender;

    public void sendEmail(String subject, String body, String emailTo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailFrom);
        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(message); //раскомментить по нужде

    }


    @EventListener(classes = ActionSendLetterCandidate.class)
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Новая транзакция
    public void sendToCandidate(ActionSendLetterCandidate action) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom);
        helper.setTo(action.getCandidateMail());
        helper.setSubject(action.getTemplateSubject());

        String link = String.format(inviteLink + "?token=%s", action.getToken());

        String emailBody = action.getTemplateBody().replace("{token}", link);

        helper.setText(emailBody,true);
        mailSender.send(message);
    }
}