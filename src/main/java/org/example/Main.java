package org.example;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        // Local postgres connection url
        // String url = "jdbc:postgresql://localhost/testdb?user=ziyan&password=postgres";

        // Docker postgres connection url
        String url = "jdbc:postgresql://0.0.0.0:5432/ziyan?user=ziyan&password=postgres";

        try {
            Connection conn = DriverManager.getConnection(url);

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM note;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                System.out.println(id + title + content + createdAt.toString());
            }

        } catch (Exception e) {
            System.out.println("Exception is" + e);
        }

    }
}