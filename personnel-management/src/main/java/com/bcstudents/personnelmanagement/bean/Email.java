package com.bcstudents.personnelmanagement.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("4900teamwork88@gmail.com");

        emailSender.send(message);

        System.out.println("Message sent successfully");
    }
}