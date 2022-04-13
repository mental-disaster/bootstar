package com.example.bootstar.mapper;

import com.example.bootstar.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    //유저정보확인
    User getUserAccount(String username);

    //회원가입
    void saveUser(User user);
}
