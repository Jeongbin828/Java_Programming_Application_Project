package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener{
	
	private JTextField textFieldId;
	private JTextField textFieldPw;
	
	private JButton btnLogin;
	private JButton signUpBtn;

	public Login(){
		setTitle("로그인");
		setSize(500, 700);
		setLocationRelativeTo(null);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_white.jpg"));
		
		JLabel labelLogin = new JLabel("로그인");
		Font font = new Font("맑은 고딕", Font.BOLD,20);
		//labelLogin.setFont(new Font("", ,));
		
		// 
		makeInputLogin();
		
		//
		makeBySignUp();
		
		JLabel sup = new JLabel("아직 회원이 아니신가요?");
		signUpBtn = new JButton("회원 가입하기");
		signUpBtn.setForeground(Color.orange);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.addActionListener(this);
		
		add(labelLogo);
		add(labelLogin);
		add(textFieldId);
		add(textFieldPw);
		add(btnLogin);
		add(sup);
		add(signUpBtn);
		setVisible(true);
	}

	private void makeBySignUp() {
		
	}

	private void makeInputLogin() {
		textFieldId = new JTextField();
		textFieldPw = new JTextField();
		
		btnLogin = new JButton("로그인");
		//btnLogin.setBackground(Color.);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.addActionListener(this);
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnLogin) {
			new Sign_Up();
		}
	}

}
