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

    public void sandMail(User user,String appUrl){
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setTo(user.getUsername());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                + ":8080/reset?token=" + user.getResetToken());

        javaMailSender.send(passwordResetEmail);
    }
}
