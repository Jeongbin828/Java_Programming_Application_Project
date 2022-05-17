package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame implements ActionListener{
	
	private JButton btnPrevious, btnPrivacy, btnSave;
	
	public User() {
		setSize(1024, 628);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(0xF4F3EF));
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_b.jpg"));
		
		//
		makeBtnPrevious();
		
		// 
		makeUser();
		
		add(labelLogo);
		add(btnPrivacy);
		
		setVisible(true);
	}

	private void makeBtnPrevious() {
		btnPrevious = new JButton(new ImageIcon("images/previous.png"));
		btnPrevious.setBackground(new Color(0xF4F3EF));
		btnPrevious.setBorderPainted(false);
		btnPrevious.setContentAreaFilled(false);		
	}

	private void makeUser() {
		btnPrivacy = new JButton("≥ª ∞Ë¡§");
		btnPrivacy.addActionListener(this);
	}

	public static void main(String[] args) {
		new User();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnPrivacy) {
			this.setVisible(false);
			new My_Account();
		}
	}

}
