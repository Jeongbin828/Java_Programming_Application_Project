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
			System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
