package jdbc3;
import java.sql.*;

public class ConnectionProvider1 {
	public static Connection con;
	public static Connection getConnection () {
		
		try {
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/DAC", 
	                    "root",    
	                    "Rewa@123"   
	                    );
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
