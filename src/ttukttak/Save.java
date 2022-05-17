package ttukttak;

import javax.swing.*;
import java.awt.*;

public class Save extends JFrame{
	
	private JButton btnPrevious;
	
	public Save() {
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//
		makeBtnPrevious();
		
		setVisible(true);
	}

	private void makeBtnPrevious() {
		btnPrevious = new JButton(new ImageIcon("images/previous.png"));
		btnPrevious.setBackground(new Color(0xF4F3EF));
		btnPrevious.setBorderPainted(false);
		btnPrevious.setContentAreaFilled(false);		
	}

	public static void main(String[] args) {

	}

}
