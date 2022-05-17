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
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	
	private JTextField textFieldId;
	private JTextField textFieldPw;
	
	private JButton btnLogin;
	private JButton signUpBtn;
	private JLabel labelQSignUp;
	private JButton btnSignUp;
	private JPanel panelInputLogin, panelBySignUp;
	private String id;
	private String pw;

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
		
		btnSignUp = new JButton("ȸ�� �����ϱ�");
		btnSignUp.setBorderPainted(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.addActionListener(this);
		
		panelBySignUp.add(labelQSignUp);
		panelBySignUp.add(btnSignUp);
	}

	private void makeInputLogin() {
		panelInputLogin = new JPanel();
		panelInputLogin.setLayout(new GridLayout(4, 1, 10, 10));
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
		
		panelInputLogin.add(labelLogin);
		panelInputLogin.add(textFieldId);
		panelInputLogin.add(textFieldPw);
		panelInputLogin.add(btnLogin);
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {
			id = textFieldId.getText();
			pw = textFieldPw.getText();
			
			String sql = "select id, pw from bban.users where id = '" + id + "' and pw = '" + pw + "'";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						"bban",
						"1111");
				stmt  = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				
				if(result.next()) {
					JOptionPane.showMessageDialog(null, "�α��� ����", "", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�α��� ����", "", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
				e1.printStackTrace();
			}
		} else if(obj == btnSignUp) {
			this.setVisible(false);
			new Sign_Up();
		}
	}

}
