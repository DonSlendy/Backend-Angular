package com.crud.api.services;

import java.util.Map;

public interface IEmailService {
    public String nuevoUsuarioSimpleMessage(String paraEmail, String destinatario, String body);
    public String nuevoUsuarioMimeMessage(String paraEmail, String destinatario, Map<String, Object> variables);

}
