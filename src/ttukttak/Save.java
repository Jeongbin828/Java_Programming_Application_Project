package ttukttak;

import javax.swing.*;

import ttukttak.Menu;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Save extends JFrame{
	
	
	private Connection conn;
	private Statement stmt;


	public Save() {
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);

		
		//
		makeSave();
		
		setVisible(true);
	}


	private void makeSave() {
		
		String sql = "";

		try {

			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			System.out.println("연결 성공");
			stmt  = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			
			this.setVisible(false);
			

			
		} catch (SQLException e1) {
			System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
			e1.printStackTrace();
		} finally {
			try {
//				result.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {

	}

}
