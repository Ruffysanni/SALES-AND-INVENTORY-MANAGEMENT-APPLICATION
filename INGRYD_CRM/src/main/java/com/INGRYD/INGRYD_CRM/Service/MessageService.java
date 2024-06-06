package com.INGRYD.INGRYD_CRM.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final JavaMailSender javaMailSender;

    public MessageService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void loginNotification(String receiver, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Login Notification");
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());

    }

    @Async
    public void registrationNotification(String receiver, String firstName) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Registration Successful!");
        String message = String.format("Dear %s,\nCongratulations!\nYou have successfully registered with Email address: %s", firstName, receiver);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());

    }

}
