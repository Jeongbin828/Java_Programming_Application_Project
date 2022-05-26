package ttukttak;

import javax.swing.*;
import java.awt.*;

public class List extends JFrame{

	public List() {
		
		setSize(200, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel image = new JLabel();
		JLabel foodname = new JLabel();
		JLabel ingredient = new JLabel();
		
		panel.add(image);
		panel.add(foodname);
		panel.add(ingredient);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new List();
	}

}
