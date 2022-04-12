package com.example.bootstar.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
public class User implements UserDetails {
    @Id
    private int user_id;
    private String username;
    private String password;
    private String nickname;
    private byte[] profileimage;
    private String email;
    private Timestamp create_time;
    private String auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        System.out.println(Collections.singletonList(new SimpleGrantedAuthority(this.auth)));
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
