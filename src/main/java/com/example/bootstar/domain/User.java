package com.example.bootstar.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
public class User implements UserDetails {
    @Id
    private int user_id;
    @Size(min = 2,max = 16,message = "아이디는 2자 이상 16자 이하로 입력하십시오")
    @Pattern(regexp="[a-zA-Z0-9]*",message = "ID는 영문자 또는 숫자만 입력할 수 있습니다")
    private String username;
    @Size(min = 2,max = 16,message = "비밀번호는 2자 이상 16자 이하로 입력하십시오")
    private String password;
    private String nickname;
    private String profileimage;
    @Email(message = "올바른 이메일을 입력하십시오")
    private String email;
    private Timestamp create_time;
    private String auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public  boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
