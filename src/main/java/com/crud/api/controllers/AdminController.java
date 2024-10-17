package com.crud.api.controllers;

import com.crud.api.models.UserModel;
import com.crud.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping("/postUser")
    public int saveUser(@RequestBody UserModel user) {
        return this.userService.insertUser(user);
    }

    @DeleteMapping(path="deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        int deleted = this.userService.deleteUser(id);
        if(deleted > 0){
            return "Usuario con ID " + id + " fue borrado";
        }else{
            return "No fue posible borrar el usuario con ID "+id;
        }
    }

    //Para modificar toda la información del usuario
    @PutMapping(path = "modifyUser/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable Long id) {
        int updated = this.userService.updateUser(request,id);
        if(updated > 0){
            return request;
        }else{
            return null;
        }
    }

    @GetMapping(path = "getUser/{id}")
    public UserModel getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
}
