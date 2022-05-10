package ttukttak;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame{
	
	public Home(){
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		Color color = new Color(0xF4F3EF);
		c.setBackground(color);
//		JToolBar toolbar = new JToolBar();
//		toolbar.add(new JLabel(new ImageIcon("images/Free_Sample_By_Wix.jpg")));
		//toolbar.setBackground(color);
		ImageIcon icon = new ImageIcon("images/logo_basic.jpg");
		JLabel label = new JLabel(icon);
		add(label, BorderLayout.NORTH);
		
//		JMenuBar mb = new JMenuBar();
//		JMenu m = new JMenu("프로젝트");
//		mb.add(m);
//		add(mb);
		
		
		//add(toolbar, BorderLayout.NORTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Home();
	}

}
