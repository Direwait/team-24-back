package ru.team24.service.domain.mail.impl;

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
import ru.team24.service.domain.mail.EmailService;
import ru.team24.service.observ.action.ActionSendLetterCandidate;
import ru.team24.service.observ.action.ActionSendLetterToManager;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String MANAGER_SUBJECT = "Статус заявки изменён";
    private static final String MANAGER_TEMPLATE = """
        <html>
            <body>
            <p>Статус заявки кандидата %s %s изменен на %s</p>
            </body>
        </html>
        """;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${app.link.url}")
    private String inviteLink;

    private final JavaMailSender mailSender;

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendToManager(ActionSendLetterToManager action) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom);
        helper.setTo(action.getManagerMail());
        helper.setSubject(MANAGER_SUBJECT);

        var format = String.format(MANAGER_TEMPLATE,
                action.getCandidateLastName(),
                action.getCandidateFirstName(),
                action.getRequestState());

        helper.setText(format, true);
        mailSender.send(message);
    }

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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