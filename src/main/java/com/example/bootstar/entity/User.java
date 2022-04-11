package com.example.bootstar.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class User {
    @Id
    private int user_id;
    private String username;
    private String password;
    private String nickname;
    private byte[] profileimage;
    private String email;
    private Timestamp create_time;
}
