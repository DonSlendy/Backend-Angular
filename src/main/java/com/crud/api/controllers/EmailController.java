package com.crud.api.controllers;

import com.crud.api.models.UserModel;
import com.crud.api.repositores.UserRepository;
import com.crud.api.services.EmailService;
import com.crud.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            //this.emailService.nuevoUsuario(usermodel.getEmail(),"Prueba","Bienvenido");

            return "Enviado";
        }else{
            return "Correo o usuario existente";
        }
    }

}
