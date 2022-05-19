package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read extends JFrame{
	

	private Container c;
	private JPanel panelTop, panelBottom, panelTitle;
	private ImageIcon imageIcon;
	private JLabel labelImage;
	private JLabel labelFoodName;
	private JLabel labelIngredient;
	private JLabel labelRecipe;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;

	public Read() {
	
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		//
		makeRead();
		
		
		add(panelTop, BorderLayout.NORTH);
		add(panelBottom, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	private void makeRead() {
		
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		
		imageIcon = new ImageIcon("");
		labelImage = new JLabel(imageIcon);
		
		panelTitle = new JPanel();
		
		labelFoodName = new JLabel();
		labelFoodName.setPreferredSize(new Dimension(300, 300));
		labelFoodName.setBackground(Color.red);
		
		labelIngredient = new JLabel();
		labelIngredient.setPreferredSize(new Dimension(300, 300));
		labelIngredient.setOpaque(true);
		labelIngredient.setBackground(Color.BLUE);
		
		panelTitle.add(labelFoodName, BorderLayout.NORTH);
		panelTitle.add(labelIngredient, BorderLayout.SOUTH);
		
		panelTop.add(labelImage, BorderLayout.NORTH);
		panelTop.add(panelTitle, BorderLayout.SOUTH);
		
		panelBottom = new JPanel();
		labelRecipe = new JLabel();
		labelRecipe.setBackground(Color.GREEN);
		
		panelBottom.add(labelRecipe);
		
	}

	public static void main(String[] args) {

	}

}
