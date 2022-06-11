package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame implements ActionListener{

private JScrollPane scroll;
private ImageIcon image;
private JButton button;
//	private JButton btnList;
//	private JButton btnSave;
//	private JPanel panelTest;
//	public test() {
//		setTitle("테스트용");
//		setSize(800, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		makeTest();
//		
//		add(panelTest);
//		
//		setVisible(true);
//		
//	}
//	private void makeTest() {
//		panelTest = new JPanel();
//		
//		btnList = new JButton();
//		btnList.setContentAreaFilled(false);
//		btnList.addActionListener(this);
//		
//		btnSave = new JButton("찜하기");
//		btnSave.addActionListener(this);
//
//		btnList.add(btnSave);
//		panelTest.add(btnList);
//		
//	}
//	public static void main(String[] args) {
//		new test();
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//
//		Object obj = e.getSource();
//		
//		if(obj == btnList) {
//			System.out.println("리스트를 선택하였습니다.");
//		} else if(obj == btnSave) {
//			System.out.println("찜하기 버튼을 선택하였습니다.");
//		}
//	}
	public test() {
		setSize(800, 800);
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel panel = new JPanel();
		ImageIcon icon = new ImageIcon("images/save_empty.png");
		button = new JButton(icon);
		button.addActionListener(this);
		
		panel.add(button);
		
		add(panel);
	
		setVisible(true);
	}
	public static void main(String[] args) {
		new test();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		image = new ImageIcon("images/save_fill.png");
		button.setIcon(image);
	}

}
