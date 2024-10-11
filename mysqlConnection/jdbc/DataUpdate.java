package jdbc;

import java.sql.*;

public class DataUpdate {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
             Statement stmt = conn.createStatement()) {

            String sql = "UPDATE student SET name = 'laxmi' WHERE id = 7";
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("Failed to update data.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
