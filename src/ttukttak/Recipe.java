package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recipe extends JFrame{

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;

	public Recipe() {
		setSize(1024, 682);
		setLocationRelativeTo(null);
		
		//
		makeShowRecipe();

		setVisible(true);
	}

	private void makeShowRecipe() {
		
		String sql = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"bban",
					"1111");
			
			stmt  = conn.createStatement();
			
			result = stmt.executeQuery(sql);
			
		} catch (SQLException e1) {
			System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
