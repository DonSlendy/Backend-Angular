package com.crud.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String nuevoUsuario(String paraEmail, String asunto, String body) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(paraEmail);
            mailMessage.setSubject(asunto);
            mailMessage.setText(body);
            mailMessage.setFrom("correo@gmail.com");

            mailSender.send(mailMessage);
            return "Correo enviado";
        } catch (Exception e) {
            return "Error::" + e;
        }

    }
}
