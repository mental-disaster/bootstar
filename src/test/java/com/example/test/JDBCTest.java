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
                            "DBurl",
                            "사용자",
                            "비밀번호")){
            System.out.println(con);
            System.out.println("연결 성공!");
        } catch (Exception e) {
            System.out.println("오류 발생!");
            e.getMessage();
        }

    }
}
