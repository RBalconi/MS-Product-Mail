package com.ms.email.adapters.outbound.smtp;

import com.ms.email.application.domain.Email;
import com.ms.email.application.ports.SendEmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailService implements SendEmailServicePort {

    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void sendEmailSmtp(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
    }
}
