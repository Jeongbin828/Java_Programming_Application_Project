package ttukttak;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class Menu extends JFrame{
	public Menu() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JMenuBar menuBar = new JMenuBar();
		
		Font font = new Font("맑은 고딕", Font.BOLD, 30);
		
		JMenu menu1 = new JMenu("한식");
		menu1.setFont(font);
		JMenu menu2 = new JMenu("일식");
		menu2.setFont(font);
		JMenu menu3 = new JMenu("양식");
		menu3.setFont(font);
		JMenu menu4 = new JMenu("중식");
		menu4.setFont(font);
		JMenu menu5 = new JMenu("아시안");
		menu5.setFont(font);
		JMenu save = new JMenu("찜하기");
		save.setFont(font);
		
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.add(menu5);
		menuBar.add(save);
		panel.add(menuBar, BorderLayout.CENTER);
		add(panel);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Menu();
	}

}
