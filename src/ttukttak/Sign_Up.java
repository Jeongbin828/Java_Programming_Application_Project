package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_Up extends JFrame implements ActionListener{
	private JButton Signupbtn;
	private JButton btn;

	public Sign_Up() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(500, 700);
		setLayout(new FlowLayout());
		Container c = getContentPane();
		Color color = new Color(0xF4F3EF);
		c.setBackground(color);
		//c.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
		ImageIcon icon = new ImageIcon("images/logo_basic.jpg");
		JLabel label1 = new JLabel(icon);
		JLabel id = new JLabel("아이디");
		JTextField idTextField = new JTextField(20);
		JLabel pw = new JLabel("비밀번호");
		JTextField pwTextField = new JTextField(20);
		JLabel pwww = new JLabel("비밀번호 확인");
		JTextField pwwwTextField = new JTextField(20);
		JLabel name = new JLabel("이름");
		JTextField nameTextField = new JTextField(20);
		Signupbtn = new JButton("회원가입");
		Signupbtn.addActionListener(this);
		JLabel label = new JLabel("이미 회원이신가요?");
		btn = new JButton("로그인 하기");
		btn.addActionListener(this);
		
		add(label1);
		add(id);
		add(idTextField);
		add(pw);
		add(pwTextField);
		add(pwww);
		add(pwwwTextField);
		add(name);
		add(nameTextField);
		add(Signupbtn);
		add(label);
		add(btn);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == Signupbtn || obj == btn) {
			new Sign_In();
		}
	}

}
