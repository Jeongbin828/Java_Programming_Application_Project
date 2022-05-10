package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Write extends JFrame implements ActionListener{
	private JButton btn;
	private JTextField foodName;
	private JTextField jaeryo;
	private JTextArea r;
	public Write() {
		setTitle("글쓰기");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("음식 이름");
		foodName = new JTextField(30);
		JLabel label1 = new JLabel("   재   료   ");
		jaeryo = new JTextField(30);
		JLabel label2 = new JLabel("레 시 피");
		r = new JTextArea(15, 30);
		btn = new JButton("올리기");
		btn.addActionListener(this);
		
		add(label);
		add(foodName);
		add(label1);
		add(jaeryo);
		add(label2);
		add(new JScrollPane(r));
		add(btn);
		
		setVisible(true);
	}
	
	public JTextField getFoodName() {
		return foodName;
	}

	public JTextField getJaeryo() {
		return jaeryo;
	}

	public JTextArea getR() {
		return r;
	}

	public static void main(String[] args) {
		new Write();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn) {
			new Recipe("레시피", this);
		}
	}

}
