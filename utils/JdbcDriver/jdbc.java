import java.sql.*;

class Main
{
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc::mysql://localhost:3306/jdbc";
        String uname = "root";
        String pwd = "";
        String query = "select * from student";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, uname, pwd);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
         
        while(rs.next())
        {
            System.err.println(rs.getInt(1) + " " + rs.getString(0));
        }

        st.close();
        conn.close();
    }
}