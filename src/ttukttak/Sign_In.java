package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_In extends JFrame implements ActionListener{
	
	private JButton loginBtn;
	private JButton signUpBtn;

	public Sign_In(){
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setSize(500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setLayout(new GridLayout());
		c.setBackground(Color.WHITE);
		
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("images/logo_white.jpg");
		JLabel label = new JLabel(icon);
		JLabel login = new JLabel("로그인");
		Font font = new Font("맑은 고딕", Font.BOLD,20);
		login.setFont(font);
		JLabel id = new JLabel("  아이디  ");
		JTextField idTextField = new JTextField(20);
		JLabel pw = new JLabel("비밀번호");
		JTextField pwTextField = new JTextField(20);
		JLabel sup = new JLabel("아직 회원이 아니신가요?");
		signUpBtn = new JButton("회원 가입하기");
		signUpBtn.setForeground(Color.orange);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.addActionListener(this);
		loginBtn = new JButton("로그인");
		loginBtn.setBorderPainted(false);
		loginBtn.addActionListener(this);
		add(label);
		add(login);
		add(id);
		add(idTextField);
		add(pw);
		add(pwTextField);
		add(loginBtn);
		add(sup);
		add(signUpBtn);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Sign_In();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == signUpBtn) {
			new Sign_Up();
		} else if(obj == loginBtn) {
			new Home();
		}
	}

}
