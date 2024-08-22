package airline;

import java.sql.*;

public class DbConn 
{ 
	Connection conn;
	Statement stmt; 
	
	public DbConn()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation", "root", "J.9010048072");
			stmt = conn.createStatement();
		}	
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
  