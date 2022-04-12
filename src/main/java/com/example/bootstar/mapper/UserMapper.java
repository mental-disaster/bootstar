package com.example.bootstar.mapper;

import com.example.bootstar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserAccount(String username);

    void saveUser(User user);
}
