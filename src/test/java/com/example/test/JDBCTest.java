package com.example.test;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {
    // MySQL Connector 의 클래스. DB 연결 드라이버 정의
    private static final String DRIVER = "드라이버";
    // DB 경로
    private static final String URL = "DBurl";
    private static final String USER = "사용자명";
    private static final String PASSWORD = "비밀번호";

    @Test
    public void testConnection() throws Exception {
        // DBMS에게 DB 연결 드라이버의 위치를 알려주기 위한 메소드
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
            System.out.println("연결 성공!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("연결 실패!");
        }
    }
}
