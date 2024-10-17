package com.crud.api.repositores;

import com.crud.api.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository implements IUserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<UserModel>getUsers(){
    String MySQL = "SELECT * FROM user";
    return (ArrayList<UserModel>) jdbcTemplate.query(MySQL, BeanPropertyRowMapper.newInstance(UserModel.class));
    }

    @Override
    public int insertUser(UserModel userModel){
        return jdbcTemplate.update("INSERT INTO user (id,firstname,last_name,password,email,rol) " +
                        "VALUES (?,?,?,?,?,?)",
                new Object[]{
                        userModel.getId(),userModel.getFirstname(),userModel.getLastName(),
                        userModel.getPassword(), userModel.getEmail(), userModel.getRol()
                });
    }
    @Override
    public int deleteUser(Long id){
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?",id);
    }

    @Override
    public int updateUser(UserModel user, Long id){
        return jdbcTemplate.update("UPDATE user SET firstname = ?, last_name = ?, email = ?," +
                " rol = ? WHERE id = ?",
                new Object[]{
                        user.getFirstname(), user.getLastName(),
                          user.getEmail(), user.getRol(), id
        });
    }

    @Override
    public UserModel getUserById(Long id){
        String MySQL = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(MySQL, new Object[]{id}, new BeanPropertyRowMapper<>(UserModel.class));
    }

    @Override
    public UserModel getByName(String user){
        List<UserModel> users = jdbcTemplate.query("SELECT * FROM user WHERE firstname = ?",
                new Object[]{user}, new BeanPropertyRowMapper<>(UserModel.class));
        if (users.isEmpty()) {
            return null; // o lanza una excepción personalizada
        }
        return users.get(0);
    }
    /*

    public UserModel getByName(String user){
        String MySQL = "SELECT * FROM user WHERE firstname = ?";
        return jdbcTemplate.queryForObject(MySQL,new Object[]{user},
                new BeanPropertyRowMapper<>(UserModel.class));
    }
     */
}
