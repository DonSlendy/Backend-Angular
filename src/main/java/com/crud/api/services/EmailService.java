package com.crud.api.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;


@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String nuevoUsuarioSimpleMessage(String paraEmail, String asunto, String body) {
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
    @Override
    public String nuevoUsuarioMimeMessage(String paraEmail, String asunto, Map<String, Object> variables) {
        try {
            //MimeMessage permite crear correos más detallados en formato MIME
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            //Sirve para seccionar el correo por partes y diferenciarlas fácilmente
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(paraEmail);
            helper.setSubject(asunto);

            //Se agrega la plantilla para el correo y usar las variables:
            Context context = new Context();
            context.setVariables(variables);
            String plantillaHTML = templateEngine.process("NuevoUsuario-Mail.html", context);
            helper.setText(plantillaHTML, true);

            ClassPathResource image = new ClassPathResource("static/images/logo.png");
            helper.addInline("logo", image);

            mailSender.send(mimeMessage);
            return "H";
        } catch (Exception e) {
            return "Error:" + e;
        }
    }

}
