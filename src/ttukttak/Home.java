package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
	
	private Image background = new ImageIcon("images/background.jpg").getImage();
	private JButton btn;
	private Container contentPane;

	public Home(){
		setTitle("¶Òµü");
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Container contentPane = getContentPane();
//		contentPane.setBackground(new Color(0xBD864F));
		
		btn = new JButton("½ÃÀÛÇÏ±â");
	    Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 30);
	    btn.setFont(font);
	    btn.setForeground(Color.orange);
	    btn.setBackground(new Color(255, 232, 120));
	    btn.setBounds(210, 350, 200, 60);
	    btn.setBorderPainted(false);
	    btn.setFocusPainted(false);
	    btn.addActionListener(this);
	      
	    contentPane = getContentPane();
	      
	    JPanel jp = new JPanel();
	    contentPane.add(jp);
	    jp.setLayout(null);
	    jp.add(btn);

		setVisible(true);
	}


	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}

	public static void main(String[] args) {
		new Home();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btn) {
			new Login();
		}
	}

}
