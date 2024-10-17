package com.crud.api.services;

import com.crud.api.models.UserModel;
import com.crud.api.repositores.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserModel userModel = this.iUserRepository.getByName(name);
        if(userModel == null){
            throw new UsernameNotFoundException(name);
        }

        //Roles
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userModel.getRol()));

        return new User(userModel.getFirstname(),userModel.getPassword(), authorities);
    }
}
