package com.INGRYD.INGRYD_CRM.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Data
public class MessageService {

  private final JavaMailSender javaMailSender;
//  private final Product product;
    public MessageService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
//        this.product = product;
    }

    //    This is a message notification to be sent to the Admin and the Sales Rep only!
    @Async
    public void sendLowStockNotification(String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//        messageHelper.setTo(receiver);
        messageHelper.setSubject("Low Stock Alert!");
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());
    }
//    This is a message notification to be sent to the Admin and the Sales Rep only!
    @Async
    public void sendVeryLowStockNotification(String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//        messageHelper.setTo(receiver);
        messageHelper.setSubject("Very Low Stock Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendOutOfStockNotification(String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//        messageHelper.setTo(receiver);
        messageHelper.setSubject("Out of Stock Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendInsufficientStockNotification(String message, String receiver) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Insufficient Stock Alert!");
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendUpdatesNotification(String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//        messageHelper.setTo(receiver);
        messageHelper.setSubject("Very Low Stock Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendSalesNotification(String message, String receiver) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Sales Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendNewProductNotification(String message, String receiver) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("New Product Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendPaymentNotification(String message, String receiver) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Payment Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async
    public void sendReceiptNotification(String receiver,String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Receipt Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    @Async
    public void sendInvoiceNotification(String receiver,String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Invoice Alert!");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

}


