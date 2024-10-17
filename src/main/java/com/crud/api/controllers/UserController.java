package com.crud.api.controllers;

import com.crud.api.models.UserModel;
import com.crud.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/peti")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping(path = "getUser/{id}")
    public UserModel getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

/*
    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable Long id){
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path ="/{id}")
    public String deleteById(@PathVariable Long id){
        boolean ok = this.userService.deleteUser(id);
        if(ok){
            return "Usuario con ID " + id + " fue borrado";
        }else{
            return "No fue posible borrar el usuario con ID "+id;
        }
    }*/
}
