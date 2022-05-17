package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
	
	private Image background = new ImageIcon("images/background.jpg").getImage();
	private JToolBar toolBar;
	private JButton btnSignIn;

	public Home(){
		setTitle("ถาตü");
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(0xBD864F));
		
		//
//		makeToolBar();
		
		setVisible(true);
	}

//	private void makeToolBar() {
//		toolBar = new JToolBar();
//		toolBar.setBackground(null);
//		toolBar.setFloatable(false);
//		
//		btnSignIn = new JButton(new ImageIcon("images/user_fill.png"));
//		btnSignIn.setBorderPainted(false);
//		btnSignIn.setContentAreaFilled(false);
//		btnSignIn.addActionListener(this);
//		
//		toolBar.add(new JButton(new ImageIcon("images/logo_basic.jpg")));
//	}
//	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}

	public static void main(String[] args) {
		new Home();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
//		if(obj == btnSignIn) {
//			new Login();
//		}
	}

}
