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

public class Sign_Up extends JFrame implements ActionListener{
	
	private JButton btnSignUp;
	private JTextField textFieldId, textFieldPw, textFieldPwCheck, textFieldName;
	private JLabel labelQLogin;
	private JButton btnByLogin;
	private JPanel panelInputSignUp, panelByLogin;
	
	private Connection conn = null;
	private Statement stmt = null;
//	private ResultSet result = null;

	public Sign_Up() {
		setTitle("ȸ������");
		setSize(400, 420);
		setResizable(false);
		
		setLayout(new FlowLayout());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);
	
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_white.jpg"));
		
		//
		makeInputSignUp();
		
		//
		makeByLogin();
		
		add(labelLogo);
		add(panelInputSignUp);
		add(panelByLogin);
		setVisible(true);
	}

	private void makeByLogin() {
		panelByLogin = new JPanel();
		panelByLogin.setLayout(new GridLayout(1, 2));
		panelByLogin.setBackground(Color.WHITE);
		
		labelQLogin = new JLabel("�̹� ȸ���̽Ű���?");
		labelQLogin.setForeground(Color.GRAY);
		
		btnByLogin = new JButton("�α��� �ϱ�");
		btnByLogin.setBorderPainted(false);
		btnByLogin.setContentAreaFilled(false);
		btnByLogin.addActionListener(this);
		
		panelByLogin.add(labelQLogin);
		panelByLogin.add(btnByLogin);
	}

	private void makeInputSignUp() {
		panelInputSignUp = new JPanel();
		panelInputSignUp.setLayout(new GridLayout(6, 1, 5, 5));
		panelInputSignUp.setBackground(Color.WHITE);
		
		JLabel labelSignUp = new JLabel("ȸ������");
		labelSignUp.setHorizontalAlignment(JLabel.CENTER);
		labelSignUp.setFont(new Font("����", Font.PLAIN, 25));
		
		textFieldId = new JTextField(20);
		textFieldPw = new JPasswordField(20);
		textFieldPwCheck = new JPasswordField(20);
		textFieldName = new JTextField(20);
		
		btnSignUp = new RoundedButton("ȸ������");
		btnSignUp.addActionListener(this);
		
		panelInputSignUp.add(labelSignUp);
		panelInputSignUp.add(textFieldId);
		panelInputSignUp.add(textFieldPw);
		panelInputSignUp.add(textFieldPwCheck);
		panelInputSignUp.add(textFieldName);
		panelInputSignUp.add(btnSignUp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSignUp) {
			String id = textFieldId.getText();
			String pw = textFieldPw.getText();
			String pwCheck = textFieldPwCheck.getText();
			String name = textFieldName.getText();
			
			String sql = "insert into users(user_id, pw, pwcheck, name) "
					+ "values('" + id + "','" + pw + "','" + pwCheck + "','" + name + "')";
			System.out.println(sql);
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
				System.out.println("���� ����");
				
				stmt  = conn.createStatement();
				int result = stmt.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(null, "ȸ������ ����", "", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
				e1.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if(obj == btnByLogin) {
			this.setVisible(false);
			new Login();
		}
	}

}
