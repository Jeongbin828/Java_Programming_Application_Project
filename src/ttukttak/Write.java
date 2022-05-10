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
		setTitle("�۾���");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("���� �̸�");
		foodName = new JTextField(30);
		JLabel label1 = new JLabel("   ��   ��   ");
		jaeryo = new JTextField(30);
		JLabel label2 = new JLabel("�� �� ��");
		r = new JTextArea(15, 30);
		btn = new JButton("�ø���");
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
			new Recipe("������", this);
		}
	}

}
