package ttukttak;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Write extends JFrame implements ActionListener{
	
	private JButton btnPrevious;
	private JPanel panelBtnPrevious, panelWrite, panelBtn;
	private JTextField textFieldFoodName, textFieldIngredient;
	private String type[] = {"한식", "일식", "양식", "기타"};
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private JTextArea textAreaRecipe;
	private JButton btnCancel, btnWrite;
	
	private Connection conn = null;
	private Statement stmt = null;
//	private ResultSet result = null;
	private String foodName, ingredient, recipe;
	private String user_id;
	private Object menuType;
	private Login login;
	private JLabel labelImage;
	private JButton btnImage;
	private JFileChooser fileChooser;
	private ImageIcon image;
	
	
	
	public Write(String login_id) {

		user_id = login_id;
		
		setTitle("글쓰기");
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(0xF4F3EF));
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_b.jpg"));
		
		
		//
		makeWrite();
		
		
		
		add(panelWrite, BorderLayout.CENTER);
		add(panelBtn, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void makeWrite() {
		
		
		panelWrite = new JPanel();
		panelWrite.setLayout(new GridLayout(9,1));
		
		
		
		labelImage = new JLabel(image);
		btnImage = new JButton("사진 첨부");
		btnImage.addActionListener(this);
		
		JLabel labelFoodName = new JLabel("음식 이름이 무엇인가요?");
		textFieldFoodName = new JTextField(30);

		comboBox = new JComboBox<String>(type);
		menuType = comboBox.getSelectedItem();
		
		
		JLabel labelIngredient = new JLabel("필요한 재료는 무엇인가요?");
		textFieldIngredient = new JTextField(30);
		
		JLabel labelRecipe = new JLabel("레시피를 알려주세요.");
		textAreaRecipe = new JTextArea(7, 20);
		scrollPane = new JScrollPane(textAreaRecipe);
		
		panelBtn = new JPanel();
		
		btnCancel = new JButton("취소");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addActionListener(this);
		
		btnWrite = new JButton("작성");
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.addActionListener(this);
		
		panelWrite.add(labelImage);
		panelWrite.add(btnImage);
		panelWrite.add(labelFoodName);
		panelWrite.add(textFieldFoodName);
		panelWrite.add(comboBox);
		panelWrite.add(labelIngredient);
		panelWrite.add(textFieldIngredient);
		panelWrite.add(labelRecipe);
		panelWrite.add(scrollPane);
		
		panelBtn.add(btnCancel);
		panelBtn.add(btnWrite);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnImage) {
				fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
				fileChooser.showOpenDialog(this);
				
				File selectedFile = fileChooser.getSelectedFile();
				try {
					BufferedImage bufferedImage = ImageIO.read(selectedFile);
					image = new ImageIcon(bufferedImage);
					labelImage.setIcon(image);
					
				} catch (IOException e2) {
					e2.printStackTrace();
				}
		} else if(obj == btnWrite) {

	
			foodName = textFieldFoodName.getText();
			ingredient = textFieldIngredient.getText();
			recipe = textAreaRecipe.getText();
			
			
			System.out.println(menuType + "" + foodName + "" + ingredient + "" + recipe );
			
			String sql = "insert into recipe(user_id, image, foodname, type, ingredient, recipe) "
					+ "values('" + user_id +"', '" + image + "', '"+ foodName + "', '" + menuType + "', '" + ingredient + "', '" + recipe + "')";
			System.out.println(sql);
			System.out.println(user_id);
			try {
				System.out.println(user_id);

				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
				System.out.println("연결 성공");
				stmt  = conn.createStatement();
				
				int result = stmt.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(null, "작성완료", "", JOptionPane.PLAIN_MESSAGE);
				
				this.setVisible(false);
				
//				new Menu();
//				new Menu(login);
				
			} catch (SQLException e1) {
				System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
				e1.printStackTrace();
			} finally {
				try {
//					result.close();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if(obj == btnCancel) {
			JOptionPane.showConfirmDialog(null, "글쓰기를 그만 두시나요?\n작성한 글이 저장되지 않아요", "", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}

}
