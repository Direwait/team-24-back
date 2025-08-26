package ru.team24.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.team24.service.EmailService;
import ru.team24.action.ActionSendLetterCandidate;
import ru.team24.action.ActionSendLetterToManager;


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
    private String frontendLink;

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "${kafka.topics.manager-email}")
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

    @KafkaListener(topics = "${kafka.topics.candidate-email}")
    public void sendToCandidate(ActionSendLetterCandidate action) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom);
        helper.setTo(action.getCandidateMail());
        helper.setSubject(action.getTemplateSubject());

        String link = String.format(frontendLink + "/registration/%s", action.getToken());

        String emailBody = action.getTemplateBody().replace("{token}", link);

        helper.setText(emailBody,true);
        mailSender.send(message);
    }

}