package com.crud.api.services;

import com.crud.api.models.UserModel;
import com.crud.api.repositores.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public ArrayList<UserModel> getUsers(){
        ArrayList<UserModel>arrayList;
        try {
            arrayList = iUserRepository.getUsers();
        }catch (Exception e){
            throw e;
        }
        return arrayList;
//        return (ArrayList<UserModel>) userRepository.findAll();
    }

    @Override
    public int insertUser(UserModel userModel){
        int user;
        try{
            user = iUserRepository.insertUser(userModel);
        }catch (Exception e){
            throw e;
        }
        return user;
    }

    @Override
    public int deleteUser(Long id){
        int deleteUser;
        try {
            deleteUser = iUserRepository.deleteUser(id);
        }catch (Exception e){
            throw e;
        }
        return deleteUser;
    }

    @Override
    public int updateUser(UserModel user, Long id){
        int updateUser;
        try {
            updateUser = iUserRepository.updateUser(user, id);
        }catch (Exception e){
            throw e;
        }
        return updateUser;
    }

    @Override
    public UserModel getUserById(Long id){
        UserModel userId;
        try {
            userId = iUserRepository.getUserById(id);
        }catch (Exception e){
            throw e;
        }
        return userId;
    }

    /*
    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();
        user.setFirstname(request.getFirstname());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return user;
    }

    public Boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }*/
}
