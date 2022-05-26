package ttukttak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read extends JFrame{
	

	private Container c;
	private JPanel panelTop, panelBottom, panelTitle;
	private ImageIcon imageIcon;
	private JLabel labelImage;
	private JLabel labelFoodName;
	private JLabel labelIngredient;
	private JLabel labelRecipe;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private String foodName;
	private String ingredient;
	private String recipe;

	public Read() {
	
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		//
		makeRead();
		
		
		add(panelTop, BorderLayout.NORTH);
		add(panelBottom, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	private void makeRead() {

		
		String sql = "select * from recipe where recipe_index = '11'";
		
		try {
			System.out.println(sql);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			System.out.println("연결 성공");
			stmt  = conn.createStatement();
		
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				
				foodName = result.getString("foodname");
				ingredient = result.getString("ingredient");
				recipe = result.getString("recipe");
				
			}
		} catch (SQLException e1) {
			System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
			e1.printStackTrace();
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		
		imageIcon = new ImageIcon();
		labelImage = new JLabel(imageIcon);
		
		panelTitle = new JPanel();
		
		labelFoodName = new JLabel(foodName);
		labelFoodName.setPreferredSize(new Dimension(300, 300));
		labelFoodName.setBackground(Color.red);
		
		labelIngredient = new JLabel(ingredient);
		labelIngredient.setPreferredSize(new Dimension(300, 300));
		labelIngredient.setOpaque(true);
		labelIngredient.setBackground(Color.BLUE);
		
		panelTitle.add(labelFoodName, BorderLayout.NORTH);
		panelTitle.add(labelIngredient, BorderLayout.SOUTH);
		
		panelTop.add(labelImage, BorderLayout.NORTH);
		panelTop.add(panelTitle, BorderLayout.SOUTH);
		
		panelBottom = new JPanel();
		labelRecipe = new JLabel(recipe);
		labelRecipe.setBackground(Color.GREEN);
		
		panelBottom.add(labelRecipe);
		}

		
	}
	public static void main(String[] args) {
		new Read();
	}

}
