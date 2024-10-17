package com.crud.api.repositores;

import com.crud.api.models.UserModel;
import java.util.ArrayList;
import java.util.Optional;

public interface IUserRepository{
    public ArrayList<UserModel>getUsers();
    public int insertUser(UserModel userModel);
    public int deleteUser(Long id);
    public int updateUser(UserModel userModel, Long id);
    public UserModel getUserById(Long id);
    public UserModel getByName(String user);
}
