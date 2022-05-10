package ttukttak;

import javax.swing.*;
import java.awt.*;

public class Recipe extends JFrame{
	private JLabel fname;
	private JLabel fjaeryo;
	private JLabel rcp;

	public Recipe(String title, Write write) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		fname = new JLabel();
		fjaeryo = new JLabel();
		rcp = new JLabel();
	
		add(fname);
		add(fjaeryo);
		add(rcp);
		setVisible(true);
	}


}
