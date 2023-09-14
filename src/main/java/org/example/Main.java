package org.example;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        bareboneConnection();
        hikariConnection();
    }

    static void bareboneConnection() {
        System.out.println("# Connect to DB directly # ");
        // Local postgres connection url
        // String url = "jdbc:postgresql://localhost/testdb?user=ziyan&password=postgres";
        // Docker postgres connection url
        String url = "jdbc:postgresql://0.0.0.0:5432/ziyan?user=ziyan&password=postgres";

        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM note;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                System.out.println(id + title + content + createdAt.toString());
            }
        } catch (SQLException e) {
            System.out.println("Get Error: " + e);
            throw new RuntimeException(e);
        }
    }

    static void hikariConnection() {
        System.out.println("# Connect to DB through connection pool # ");
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://0.0.0.0:5432/ziyan");
        ds.setUsername("ziyan");
        ds.setPassword("postgres");

        try (Connection conn = ds.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM note;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                System.out.println(id + title + content + createdAt.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}