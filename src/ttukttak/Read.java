package ttukttak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

public class Read extends JFrame implements ActionListener{
	

	private ImageIcon imageIcon;
	private JLabel labelImage;
	private JLabel labelFoodName;
	private JLabel labelIngredient;
	private JLabel labelRecipe;
	private String foodName;
	private String ingredient;
	private String recipe;
	private BufferedImage bimage;
	private String image;
	private JPanel panelRead;
	private Font name_font, main_font, btn_font;
	private RoundedButton btnMenu;
	private String index;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;

//	public Read() {
//	
//	}

	public Read(String recipeIndex) {
		
		this.index = recipeIndex;
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		name_font = new Font("카페24 써라운드", 0,30);
		main_font = new Font("카페24 써라운드", 0,14);
		btn_font = new Font("카페24 써라운드", 0, 12);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		//
		makeRead();
		
		add(panelRead, BorderLayout.CENTER);
		
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

	private void makeRead() {

		String sql = "select image, foodname, ingredient, recipe from recipe where recipe_index = '"+ index + "'";
		
		try {
			System.out.println(sql);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			stmt  = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				// 데이터베이스에 저장된 이미지 불러오기
//				InputStream image = result.getBinaryStream("image");
//				bimage = ImageIO.read(image);
				
				//				
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
//		} catch (IOException e) {
//			System.out.println("저장된 이미지가 없습니다.");
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	        
	         panelRead = new JPanel();
	         panelRead.setBackground(Color.WHITE);
//	         panelRead.setLayout(null);
	         panelRead.setLayout(new FlowLayout());
	          
	         //
//	         imageIcon = new ImageIcon(bimage);
//	         labelImage = new JLabel(imageIcon);
	         labelImage = new JLabel("ㅇㅇ");
	         labelImage.setBounds(330, 110, 115, 125);
	            
	         //
	         labelFoodName = new JLabel(foodName);
	         labelFoodName.setBounds(480, 30, 300, 30);
	         labelFoodName.setFont(name_font);
	            
	         //
	         labelIngredient = new JLabel(ingredient);
	         labelIngredient.setBounds(480, 80, 300, 30);
	         labelIngredient.setFont(main_font);
	            
	         //
	         labelRecipe = new JLabel(recipe);
	         labelRecipe.setBounds(340, 170, 300, 30);
	         labelRecipe.setFont(main_font);
	          
	         //
	         btnMenu = new RoundedButton("메뉴로 돌아가기");
	         btnMenu.setBounds(570, 450, 130, 30);
	         btnMenu.setFont(btn_font);
	         btnMenu.setBorderPainted(false);
	         btnMenu.addActionListener(this);
	            
	         panelRead.add(labelImage);
	         panelRead.add(labelFoodName);
	         panelRead.add(labelIngredient);
	         panelRead.add(labelRecipe);
	         panelRead.add(btnMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMenu) {
			this.setVisible(false);
		}
	}

}
