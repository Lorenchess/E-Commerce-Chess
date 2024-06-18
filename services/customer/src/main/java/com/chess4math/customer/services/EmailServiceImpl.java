package com.chess4math.customer.services;

import com.chess4math.customer.config.EmailConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final MailSender mailSender;


    private final EmailConfigProperties emailConfigProperties;

    private SimpleMailMessage templateMessage;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailConfigProperties emailConfigProperties) {
        this.mailSender = mailSender;
        this.emailConfigProperties = emailConfigProperties;
        this.templateMessage = new SimpleMailMessage();
        this.templateMessage.setFrom(emailConfigProperties.getFromAddress());
    }



    @Async
    @Override
    public void sendRegisterConfirmation(String recipient, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(this.templateMessage);
        mailMessage.setTo(recipient);
        mailMessage.setText(content);
        mailMessage.setSubject(subject);
        try{
            mailSender.send(mailMessage);
        } catch (MailException ex) {
            logger.warn(ex.getMessage());
        }

    }
}
