package com.example.test;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        try(Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:포트번호/DB명",
                            "사용자",
                            "비밀번호")){
            System.out.println(con);
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
