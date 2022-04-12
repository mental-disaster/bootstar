package com.example.bootstar.mapper;

import com.example.bootstar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    //로그인
    User getUserAccount(String username);

    //회원가입
    void saveUser(User user);
}
