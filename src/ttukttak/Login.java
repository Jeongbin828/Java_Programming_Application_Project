package ttukttak;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener{
	
	
	 private JTextField textFieldId;
	   private JTextField textFieldPw;
	   
	   private JButton btnLogin, btnBySignUp;
	   private JLabel labelQSignUp;
	   private JPanel panelInputLogin, panelBySignUp;
	   private String id, pw;
	   private JLabel label;
	   
	   private Connection conn = null;
	   private Statement stmt = null;
	   private ResultSet result = null;
	   private String user_id;
	   private Font main_font;
	   private Font sub_font;
	   private JLabel labelLogo;
	   private JPanel panelText;

	   public Login(){
	      setTitle("로그인");
	      setSize(400, 400);
	      setLocationRelativeTo(null);
	      setResizable(false);
	      
	      setLayout(new BorderLayout());

	      Container contentPane = getContentPane();
	      contentPane.setBackground(Color.WHITE);
	      
	      main_font = new Font("카페24 써라운드", 0,12);
	      sub_font = new Font("카페24 써라운드", 0, 20);
	      
	      //
	      makeInputLogin();
	      
	      //
	      makeBySignUp();
	      
	      add(panelInputLogin, BorderLayout.NORTH);
	      add(panelText,BorderLayout.CENTER);
	      add(panelBySignUp, BorderLayout.SOUTH);
	      
	      setVisible(true);
	   }

	   private void makeBySignUp() {
	      panelBySignUp = new JPanel();
	      panelBySignUp.setLayout(new GridLayout(1, 2));
	      panelBySignUp.setBackground(Color.WHITE);
	      
	      labelQSignUp = new JLabel("아직 회원이 아니신가요?");
	      labelQSignUp.setFont(main_font);
	      labelQSignUp.setForeground(Color.GRAY);
	      labelQSignUp.setBorder(BorderFactory.createEmptyBorder(0, 65, 40, 0));
	      
	      btnBySignUp = new JButton("회원 가입하기");
	      btnBySignUp.setFont(main_font);
	      btnBySignUp.setBorderPainted(false);
	      btnBySignUp.setContentAreaFilled(false);
	      btnBySignUp.addActionListener(this);
	      btnBySignUp.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 30));
	      
	      panelBySignUp.add(labelQSignUp);
	      panelBySignUp.add(btnBySignUp);
	   }

	   private void makeInputLogin() {
	      panelInputLogin = new JPanel();
	      panelInputLogin.setLayout(new GridLayout(2, 1, 4, 5));
	      panelInputLogin.setBackground(Color.WHITE);
	      panelInputLogin.setPreferredSize(new Dimension(0, 140));
	      
	      labelLogo = new JLabel(new ImageIcon("images/logo.jpg"));
	      
	      JLabel labelLogin = new JLabel("로그인");
	      labelLogin.setHorizontalAlignment(JLabel.CENTER);
	      labelLogin.setFont(sub_font);
	      
	      
	      panelText = new JPanel();
	      panelText.setLayout(null);
	      panelText.setBackground(Color.WHITE);
	      
	      ImageIcon id = new ImageIcon("images/id.png");
	      JLabel labelId = new JLabel(id);
	      labelId.setBounds(60, 0, 30, 30);
	      
	      textFieldId = new JTextField(20);
	      textFieldId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
	      textFieldId.setBounds(100, 0, 200, 30);
	      
	      ImageIcon pw = new ImageIcon("images/pw.png");
	      JLabel labelPw = new JLabel(pw);
	      labelPw.setBounds(60, 40, 30, 30);
	      
	      textFieldPw = new JPasswordField(20);
	      textFieldPw.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
	      textFieldPw.setBounds(100, 40, 200, 30);
	      
	      btnLogin = new RoundedButton("로그인");
	      btnLogin.setFont(new Font("카페24 써라운드", 0, 15));
	      btnLogin.setBounds(100, 110, 200, 30);
	      btnLogin.setBackground(Color.BLACK);
	      btnLogin.setForeground(Color.WHITE);
	      btnLogin.setBorderPainted(false);
	      btnLogin.addActionListener(this);
	      
	      label = new JLabel("");
	      label.setBounds(90, 85, 300, 25);
	      
	      panelInputLogin.add(labelLogo);
	      panelInputLogin.add(labelLogin);
	      
	      panelText.add(labelId);
	      panelText.add(textFieldId);
	      panelText.add(labelPw);
	      panelText.add(textFieldPw);
	      panelText.add(label);
	      panelText.add(btnLogin);
	   }


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {	// 로그인 버튼을 누르면,
			id = textFieldId.getText();
			pw = textFieldPw.getText();
			
			String sql = "select user_id, pw from users where user_id = '" + id + "' and pw = '" + pw + "'";
			System.out.println(sql);
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");

				stmt  = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				
				if(result.next()) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "", JOptionPane.PLAIN_MESSAGE);
					
					user_id = result.getString("USER_ID");
					this.setVisible(false);
					new Menu(id);

				} else {
					label.setText("아이디 또는 비밀번호를 잘못 입력했습니다.");
					label.setForeground(Color.RED);
					textFieldId.setText("");
					textFieldPw.setText("");
				}
				
			} catch (SQLException e1) {
				System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
				e1.printStackTrace();
			}
		} else if(obj == btnBySignUp) {	// 회원가입 버튼을 누르면,
			this.setVisible(false);
			new Sign_Up();
		}
	}

	public String getUser_id() {
		return user_id;
	}



}
