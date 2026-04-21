package com.academy;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * application.properties의 DB 연결정보를 사용하여
 * MySQL 데이터베이스 연결을 테스트하는 클래스
 */
public class DBConnectionTest {

    public static void main(String[] args) {
        String url = null;
        String username = null;
        String password = null;
        String driverClassName = null;

        // application.properties 파일 로드
        try (InputStream input = DBConnectionTest.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                System.out.println("application.properties 파일을 찾을 수 없습니다.");
                return;
            }

            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("spring.datasource.url");
            username = prop.getProperty("spring.datasource.username");
            password = prop.getProperty("spring.datasource.password");
            driverClassName = prop.getProperty("spring.datasource.driver-class-name");

            System.out.println("========== DB 연결 정보 ==========");
            System.out.println("Driver: " + driverClassName);
            System.out.println("URL: " + url);
            System.out.println("Username: " + username);
            System.out.println("==================================");

        } catch (Exception e) {
            System.out.println("properties 파일 로드 실패: " + e.getMessage());
            return;
        }

        // JDBC 드라이버 로드
        try {
            Class.forName(driverClassName);
            System.out.println("JDBC 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
            return;
        }

        // DB 연결 테스트
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            if (conn != null) {
                System.out.println("DB 연결 성공!");
                System.out.println("Database: " + conn.getCatalog());
                System.out.println("Connection Valid: " + conn.isValid(5));
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}