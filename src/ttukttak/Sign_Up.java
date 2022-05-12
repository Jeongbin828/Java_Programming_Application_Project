package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_Up extends JFrame implements ActionListener{
	
	private JButton btnSignUp;
	private JTextField textFieldId;
	private JLabel labelQLogin;
	private JButton btnByLogin;

	public Sign_Up() {
		setSize(500, 700);
		setLayout(new FlowLayout());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);
	
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_white.jpg"));
		
		JLabel labelSignUp = new JLabel("회원가입");
		//labelSignUp.setFont(new Font());
		
		//
		makeInputSignUp();
		
		//
		makeByLogin();
		
		add(labelLogo);
		add(labelSignUp);
		setVisible(true);
	}

	private void makeByLogin() {
		labelQLogin = new JLabel("이미 회원이신가요?");
		
		btnByLogin = new JButton("로그인 하기");
		btnByLogin.addActionListener(this);
	}

	private void makeInputSignUp() {
		//textFieldId = new JTextField();
		
		btnSignUp = new JButton("회원가입");
		btnSignUp.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

}
