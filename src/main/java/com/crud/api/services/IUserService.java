package com.crud.api.services;

import com.crud.api.models.UserModel;
import org.apache.catalina.User;

import java.util.ArrayList;

public interface IUserService {
    public ArrayList<UserModel> getUsers();
    public int insertUser(UserModel userModel);
    public int deleteUser(Long id);
    public int updateUser(UserModel user, Long id);
    public UserModel getUserById(Long id);

    public UserModel getByNameAndEmail(UserModel user);
}
