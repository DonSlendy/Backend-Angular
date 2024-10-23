package com.crud.api.controllers;

import com.crud.api.models.UserModel;
import com.crud.api.repositores.UserRepository;
import com.crud.api.services.EmailService;
import com.crud.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/new-user")
    public String postNuevoUsuario(@RequestBody UserModel usermodel){
        if(this.userService.getByNameAndEmail(usermodel) == null){
            //this.emailService.nuevoUsuarioSimpleMessage(usermodel.getEmail(),"Prueba","Bienvenido");
            Map<String,Object> variables = new HashMap<>();
            variables.put("nameUser",usermodel.getFirstname());
            this.emailService.nuevoUsuarioMimeMessage(usermodel.getEmail(),"Bienvenida",variables);
            return "Enviado";
        }else{
            return "Correo o usuario existente";
        }
    }

}
