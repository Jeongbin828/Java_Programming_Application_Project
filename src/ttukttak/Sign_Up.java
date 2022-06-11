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
   private JPanel panelText;
   private Font font;
   private JLabel errorId;
   private JLabel errorPw;
   private ResultSet result;
   private String id;
   private String sql;
   public Sign_Up() {
      setTitle("ȸ������");
      setSize(400, 460);
      setResizable(false);
      
      setLayout(new BorderLayout());
      
      Container contentPane = getContentPane();
      contentPane.setBackground(Color.WHITE);
      setLocationRelativeTo(null);
   
      font = new Font("ī��24 �����", 0, 12);
      
      makeInputSignUp();
      
      makeByLogin();
      
      add(panelInputSignUp, BorderLayout.NORTH);
      add(panelByLogin, BorderLayout.SOUTH);
      add(panelText, BorderLayout.CENTER);
      setVisible(true);
   }

   private void makeByLogin() {
      panelByLogin = new JPanel();
      panelByLogin.setLayout(new GridLayout(1, 2));
      panelByLogin.setBackground(Color.WHITE);
      
      labelQLogin = new JLabel("�̹� ȸ���̽Ű���?");
      labelQLogin.setFont(font);
      labelQLogin.setForeground(Color.GRAY);
      labelQLogin.setBorder(BorderFactory.createEmptyBorder(0, 90, 30, 0));
      
      btnByLogin = new JButton("�α��� �ϱ�");
      btnByLogin.setFont(font);
      btnByLogin.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 60));
      btnByLogin.setBorderPainted(false);
      btnByLogin.setContentAreaFilled(false);
      btnByLogin.addActionListener(this);
      
      panelByLogin.add(labelQLogin);
      panelByLogin.add(btnByLogin);
   }

   private void makeInputSignUp() {
      panelInputSignUp = new JPanel();
      panelInputSignUp.setLayout(new GridLayout(2, 1, 5, 5));
      panelInputSignUp.setBackground(Color.WHITE);
      panelInputSignUp.setPreferredSize(new Dimension(0, 140)); //�г� ũ�� ����
   
      JLabel labelLogo = new JLabel(new ImageIcon("images/logo.jpg"));
      
      JLabel labelSignUp = new JLabel("ȸ������");
      labelSignUp.setHorizontalAlignment(JLabel.CENTER);
      labelSignUp.setFont(new Font("ī��24 �����", 0, 20));
      
      
      panelText = new JPanel();
      panelText.setLayout(null);
      panelText.setBackground(Color.WHITE);
      
      JLabel noticeId = new JLabel("���̵�");
      noticeId.setBounds(70, 0, 50, 30);
      noticeId.setFont(font);
      
      textFieldId = new JTextField(20);
      textFieldId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
      textFieldId.setFont(font);
      textFieldId.setBounds(115, -5, 200, 26);
      
      errorId = new JLabel();
      errorId.setText("");
      errorId.setFont(new Font("ī��24 �����", 0, 11));
      errorId.setForeground(Color.RED);
      errorId.setBounds(120, 21, 300, 25);
      
      JLabel noticePw = new JLabel("��й�ȣ");
      noticePw.setBounds(58, 45, 50, 30);
      noticePw.setFont(font);
      textFieldPw = new JPasswordField(20);
      textFieldPw.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
      textFieldPw.setBounds(115, 40, 200, 26);
      
      JLabel noticePwCheck = new JLabel("��й�ȣ Ȯ��");
      noticePwCheck.setBounds(32, 90, 100, 30);
      noticePwCheck.setFont(font);
      textFieldPwCheck = new JPasswordField(20);
      textFieldPwCheck.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
      textFieldPwCheck.setBounds(115, 85, 200, 26);
      
      errorPw = new JLabel();
      errorPw.setText("");
      errorPw.setFont(new Font("ī��24 �����", 0, 11));
      errorPw.setForeground(Color.RED);
      errorPw.setBounds(120, 111, 300, 25);
      
      JLabel noticeName = new JLabel("�̸�");
      noticeName.setBounds(80, 135, 100, 30);
      noticeName.setFont(font);
      
      textFieldName = new JTextField(20);
      textFieldName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
      textFieldName.setFont(font);
      textFieldName.setBounds(115, 130, 200, 26);
      
      btnSignUp = new RoundedButton("ȸ������");
      btnSignUp.setBounds(90, 187, 200, 28);
      btnSignUp.setFont(new Font("ī��24 �����", 0, 10));
      btnSignUp.addActionListener(this);
      
      panelInputSignUp.add(labelLogo);
      panelInputSignUp.add(labelSignUp);
      
      panelText.add(noticeId);
      panelText.add(textFieldId);
      panelText.add(errorId);
      panelText.add(noticePw);
      panelText.add(textFieldPw);
      panelText.add(noticePwCheck);
      panelText.add(textFieldPwCheck);
      panelText.add(errorPw);
      panelText.add(noticeName);
      panelText.add(textFieldName);
      panelText.add(btnSignUp);
   }


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSignUp) {
			
			// ���� ����
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
				System.out.println("���� ����");

			
				id = textFieldId.getText();
				sql = "select user_id from users where user_id = '" + id +"'";
				stmt = conn.createStatement();
				result = stmt.executeQuery(sql);
				System.out.println(sql);
				
				if(result.next()) {
					errorId.setText("�ߺ��� ���̵��Դϴ�.");
				}else {
					if(textFieldPw.getText().equals(textFieldPwCheck.getText())) {
						String pw = textFieldPw.getText();
						String name = textFieldName.getText();
						
						sql = "insert into users(user_id, pw, name)" 
								+ "values('" + id + "', '" + pw + "', '" + name +"')";
						
						stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						
						JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�", "", JOptionPane.PLAIN_MESSAGE);
						
						this.setVisible(false);
						new Login();
					}else {
						errorPw.setText("��й�ȣ�� Ȯ�����ּ���.");
					}
				}
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
