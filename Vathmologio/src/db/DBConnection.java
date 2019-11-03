package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getMysqlConnection() {
		String driver="org.postgresql.Driver";
		String url="jdbc:postgresql://localhost:5432/Vathmologio";
		String user="postgres";
		String pass="2xomWe1223";
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pass);
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}
}
