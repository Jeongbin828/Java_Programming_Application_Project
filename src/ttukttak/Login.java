package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public Login(){
		setTitle("로그인");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new FlowLayout());

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_white.jpg"));
		
		// 
		makeInputLogin();
		
		//
		makeBySignUp();
		
		add(labelLogo);
		add(panelInputLogin);
		add(panelBySignUp);
		setVisible(true);
	}

	private void makeBySignUp() {
		panelBySignUp = new JPanel();
		panelBySignUp.setLayout(new GridLayout(1, 2));
		panelBySignUp.setBackground(Color.WHITE);
		
		labelQSignUp = new JLabel("아직 회원이 아니신가요?");
		labelQSignUp.setForeground(Color.GRAY);
		
		btnBySignUp = new JButton("회원 가입하기");
		btnBySignUp.setBorderPainted(false);
		btnBySignUp.setContentAreaFilled(false);
		btnBySignUp.addActionListener(this);
		
		panelBySignUp.add(labelQSignUp);
		panelBySignUp.add(btnBySignUp);
	}

	private void makeInputLogin() {
		panelInputLogin = new JPanel();
		panelInputLogin.setLayout(new GridLayout(5, 1, 10, 10));
		panelInputLogin.setBackground(Color.WHITE);
		
		JLabel labelLogin = new JLabel("로그인");
		labelLogin.setHorizontalAlignment(JLabel.CENTER);
		Font font = new Font("맑은 고딕", Font.PLAIN,25);
		labelLogin.setFont(font);
		
		textFieldId = new JTextField(20);
		//textFieldId.setFont(new Font());
		//textFieldId.setBorder(null);
		
		textFieldPw = new JPasswordField(20);
		//textFieldPw.setFont(new Font());
		//textFieldPw.setBorder(null);
		
		btnLogin = new RoundedButton("로그인");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.addActionListener(this);
		
		label = new JLabel();
		
		panelInputLogin.add(labelLogin);
		panelInputLogin.add(textFieldId);
		panelInputLogin.add(textFieldPw);
		panelInputLogin.add(label);
		panelInputLogin.add(btnLogin);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {
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
					label.setText("");
					this.setVisible(false);
					new Menu(this);

				} else {
					label.setText("아이디, 비밀번호를 다시 입력해주세요.");
					label.setForeground(Color.RED);
//					JOptionPane.showMessageDialog(null, "로그인 실패", "", JOptionPane.ERROR_MESSAGE);
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
		} else if(obj == btnBySignUp) {
			this.setVisible(false);
			new Sign_Up();
		}
	}

	public String getUser_id() {
		return user_id;
	}

}
