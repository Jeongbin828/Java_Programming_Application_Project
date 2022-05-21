package ttukttak;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
	

	public static void main(String[] args) {
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
