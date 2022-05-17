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

public class Write extends JFrame implements ActionListener{
	
	private JButton btnPrevious;
	private JPanel panelWrite;
	private JTextField textFieldFoodName, textFieldIngredient;
	private JScrollPane scrollPane;
	private JTextArea textAreaRecipe;
	private JButton btnCancel, btnWrite;
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	
	public Write() {
		setTitle("�۾���");
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(0xF4F3EF));
		
		JLabel labelLogo = new JLabel(new ImageIcon("images/logo_b.jpg"));
		
		
		//
		makeBtnPrevious();
		
		//
		makeWrite();
		
		add(btnPrevious, BorderLayout.NORTH);
		add(panelWrite, BorderLayout.CENTER);
		add(btnCancel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void makeBtnPrevious() {
		btnPrevious = new JButton(new ImageIcon("images/previous.png"));
		btnPrevious.setBackground(new Color(0xF4F3EF));
		btnPrevious.setBorderPainted(false);
		btnPrevious.setContentAreaFilled(false);
		btnPrevious.addActionListener(this);
		
	}

	private void makeWrite() {
		panelWrite = new JPanel();
		
		JLabel labelFoodName = new JLabel("�̸� : ");
		textFieldFoodName = new JTextField(30);
		
		JLabel labelIngredient = new JLabel("��� : ");
		textFieldIngredient = new JTextField(30);
		
		JLabel labelRecipe = new JLabel("������ : ");
		textAreaRecipe = new JTextArea(7, 20);
		scrollPane = new JScrollPane(textAreaRecipe);
		
		
		btnCancel = new JButton("���");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addActionListener(this);
		
		btnWrite = new JButton("�ۼ�");
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.addActionListener(this);
		
		panelWrite.add(labelFoodName);
		panelWrite.add(textFieldFoodName);
		panelWrite.add(labelIngredient);
		panelWrite.add(textFieldIngredient);
		panelWrite.add(labelRecipe);
		panelWrite.add(scrollPane);
	}

	public static void main(String[] args) {
		new Write();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnWrite) {
			String foodName = textFieldFoodName.getText();
			String ingredient = textFieldIngredient.getText();
			String recipe = textAreaRecipe.getText();

			
			String sql = "insert into bban.write(foodname, ingredient, recipe) "
					+ "values('" + foodName + "', '" + ingredient + "', '" + recipe + "')";
			System.out.println(sql);
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						"bban",
						"1111");
				stmt  = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
				e1.printStackTrace();
			}
		} else if(obj == btnCancel) {
			JOptionPane.showConfirmDialog(null, "�ۼ��� �׸��ϰڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
			
			
		} else if(obj == btnPrevious) {
			
			JOptionPane.showMessageDialog(null, "�α��� ����", "", JOptionPane.PLAIN_MESSAGE);
			this.setVisible(false);
			new Menu();
		}
	}

}
