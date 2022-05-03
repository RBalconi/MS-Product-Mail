package com.ms.email.application.services;

import com.ms.email.application.domain.Email;
import com.ms.email.application.domain.enumerations.Situation;
import com.ms.email.application.ports.EmailRepositoryPort;
import com.ms.email.application.ports.EmailServicePort;
import com.ms.email.application.ports.SendEmailServicePort;
import org.springframework.mail.MailException;

import java.time.LocalDateTime;

public class EmailServiceImpl implements EmailServicePort {

    private final EmailRepositoryPort emailRepositoryPort;
    private final SendEmailServicePort sendEmailServicePort;

    public EmailServiceImpl(EmailRepositoryPort emailRepositoryPort, SendEmailServicePort sendEmailServicePort) {
        this.emailRepositoryPort = emailRepositoryPort;
        this.sendEmailServicePort = sendEmailServicePort;
    }

    @Override
    public Email sendEmail(Email email) {
        email.setSendDate(LocalDateTime.now());
        try {
            sendEmailServicePort.sendEmailSmtp(email);

            email.setSituation(Situation.SENT);
        } catch (MailException e) {
            email.setSituation(Situation.ERROR);
        } finally {
            return saveEmail(email);
        }
    }

    @Override
    public Email saveEmail(Email email) {
        return emailRepositoryPort.save(email);
    }
}
