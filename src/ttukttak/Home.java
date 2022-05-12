package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
	
	private JToolBar toolBar;
	private JButton btnSignIn;

	public Home(){
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(2, 1));
		contentPane.setBackground(new Color(0xF4F3EF));
		
		ImageIcon logo = new ImageIcon("images/logo_basic.jpg");
		JLabel label = new JLabel(logo);
		
		//
		makeToolBar();
		
		add(label);
		add(toolBar);
		
		setVisible(true);
	}

	private void makeToolBar() {
		toolBar = new JToolBar();
		toolBar.setBackground(new Color(0xF4F3EF));
		toolBar.setFloatable(false);
		
		btnSignIn = new JButton(new ImageIcon("images/user_fill.png"));
		btnSignIn.setBorderPainted(false);
		btnSignIn.addActionListener(this);
		
		toolBar.add(btnSignIn);
	}

	public static void main(String[] args) {
		new Home();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSignIn) {
			new Login();
		}
	}

}
