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
	private String type[] = {"�ѽ�", "�Ͻ�", "���", "��Ÿ"};
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
		
		setTitle("�۾���");
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
		btnImage = new JButton("���� ÷��");
		btnImage.addActionListener(this);
		
		JLabel labelFoodName = new JLabel("���� �̸��� �����ΰ���?");
		textFieldFoodName = new JTextField(30);

		comboBox = new JComboBox<String>(type);
		menuType = comboBox.getSelectedItem();
		
		
		JLabel labelIngredient = new JLabel("�ʿ��� ���� �����ΰ���?");
		textFieldIngredient = new JTextField(30);
		
		JLabel labelRecipe = new JLabel("�����Ǹ� �˷��ּ���.");
		textAreaRecipe = new JTextArea(7, 20);
		scrollPane = new JScrollPane(textAreaRecipe);
		
		panelBtn = new JPanel();
		
		btnCancel = new JButton("���");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addActionListener(this);
		
		btnWrite = new JButton("�ۼ�");
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
				System.out.println("���� ����");
				stmt  = conn.createStatement();
				
				int result = stmt.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(null, "�ۼ��Ϸ�", "", JOptionPane.PLAIN_MESSAGE);
				
				this.setVisible(false);
				
//				new Menu();
//				new Menu(login);
				
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
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
			JOptionPane.showConfirmDialog(null, "�۾��⸦ �׸� �νó���?\n�ۼ��� ���� ������� �ʾƿ�", "", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}

}
