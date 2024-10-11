package jdbc;

import java.sql.*;

public class DataDeletion{
    public static void main(String[] args) 
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
             Statement stmt = conn.createStatement()) 
        {
            String sql = "DELETE FROM student WHERE id = 5";
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
