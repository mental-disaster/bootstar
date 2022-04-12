package com.example.bootstar.Service;

import com.example.bootstar.entity.User;
import com.example.bootstar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Transactional
    public void joinUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuth("USER");
        userMapper.saveUser(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userMapper.getUserAccount(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("there is no user name {0}", username));
        }
        return user;
    }
}
