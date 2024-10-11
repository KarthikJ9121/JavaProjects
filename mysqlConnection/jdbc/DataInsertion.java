package jdbc;

import java.sql.*;

public class DataInsertion {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
             Statement stmt = conn.createStatement()) {

            String sql = "INSERT INTO student VALUES (6, 'Kumar'),(7,'raju')";
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
