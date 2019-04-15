package com.ttn.reap.service;

import com.ttn.reap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    public JavaMailSender javaMailSender;

    public void sandMail(User user,String message,String subject){
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setTo(user.getUsername());
        passwordResetEmail.setSubject(subject);
        passwordResetEmail.setText(message);

        javaMailSender.send(passwordResetEmail);
    }
}
