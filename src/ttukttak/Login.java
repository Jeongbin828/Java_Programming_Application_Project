package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener{
	
	
	private JTextField textFieldId;
	private JTextField textFieldPw;
	
	private JButton btnLogin, btnBySignUp;
	private JLabel labelQSignUp;
	private JPanel panelInputLogin, panelBySignUp;
	private String id, pw;
	private JLabel label;
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private String user_id;

	public Login(){
		setTitle("�α���");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new FlowLayout());

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_white.jpg"));
		
		// 
		makeInputLogin();
		
		//
		makeBySignUp();
		
		add(labelLogo);
		add(panelInputLogin);
		add(panelBySignUp);
		setVisible(true);
	}

	private void makeBySignUp() {
		panelBySignUp = new JPanel();
		panelBySignUp.setLayout(new GridLayout(1, 2));
		panelBySignUp.setBackground(Color.WHITE);
		
		labelQSignUp = new JLabel("���� ȸ���� �ƴϽŰ���?");
		labelQSignUp.setForeground(Color.GRAY);
		
		btnBySignUp = new JButton("ȸ�� �����ϱ�");
		btnBySignUp.setBorderPainted(false);
		btnBySignUp.setContentAreaFilled(false);
		btnBySignUp.addActionListener(this);
		
		panelBySignUp.add(labelQSignUp);
		panelBySignUp.add(btnBySignUp);
	}

	private void makeInputLogin() {
		panelInputLogin = new JPanel();
		panelInputLogin.setLayout(new GridLayout(5, 1, 10, 10));
		panelInputLogin.setBackground(Color.WHITE);
		
		JLabel labelLogin = new JLabel("�α���");
		labelLogin.setHorizontalAlignment(JLabel.CENTER);
		Font font = new Font("���� ���", Font.PLAIN,25);
		labelLogin.setFont(font);
		
		textFieldId = new JTextField(20);
		//textFieldId.setFont(new Font());
		//textFieldId.setBorder(null);
		
		textFieldPw = new JPasswordField(20);
		//textFieldPw.setFont(new Font());
		//textFieldPw.setBorder(null);
		
		btnLogin = new RoundedButton("�α���");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.addActionListener(this);
		
		label = new JLabel();
		
		panelInputLogin.add(labelLogin);
		panelInputLogin.add(textFieldId);
		panelInputLogin.add(textFieldPw);
		panelInputLogin.add(label);
		panelInputLogin.add(btnLogin);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {
			id = textFieldId.getText();
			pw = textFieldPw.getText();
			
			String sql = "select user_id, pw from users where user_id = '" + id + "' and pw = '" + pw + "'";
			System.out.println(sql);
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");

				stmt  = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				
				if(result.next()) {
					JOptionPane.showMessageDialog(null, "�α��� ����", "", JOptionPane.PLAIN_MESSAGE);
					
					user_id = result.getString("USER_ID");
					label.setText("");
					this.setVisible(false);
					new Menu(this);

				} else {
					label.setText("���̵�, ��й�ȣ�� �ٽ� �Է����ּ���.");
					label.setForeground(Color.RED);
//					JOptionPane.showMessageDialog(null, "�α��� ����", "", JOptionPane.ERROR_MESSAGE);
					textFieldId.setText("");
					textFieldPw.setText("");
				}
				
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
				e1.printStackTrace();
			}
		} else if(obj == btnBySignUp) {
			this.setVisible(false);
			new Sign_Up();
		}
	}

	public String getUser_id() {
		return user_id;
	}

}
