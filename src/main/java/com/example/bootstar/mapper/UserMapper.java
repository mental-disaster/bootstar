package com.example.bootstar.mapper;

import com.example.bootstar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveUser(User user);
}
