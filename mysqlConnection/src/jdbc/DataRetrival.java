package jdbc;

import java.sql.*;

public class DataRetrival {

	public static void main(String[] args) 
	{
		DbConn conn = new DbConn();
		String str = "select * from student";
		try
		{
			ResultSet rs = conn.stmt.executeQuery(str);
			
			while(rs.next())
			{
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

//
//package jdbc;
//
//import java.sql.*;
//
//public class SelectDataWithCondition {
//    public static void main(String[] args) {
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
//             Statement stmt = conn.createStatement()) {
//
//            String sql = "SELECT * FROM student WHERE id = 101";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
