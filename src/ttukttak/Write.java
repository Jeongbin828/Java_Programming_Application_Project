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
	private JPanel panelBtnPrevious, panelWrite, panelBtn;
	private JTextField textFieldFoodName, textFieldIngredient;
	private String type[] = {"�ѽ�", "�Ͻ�", "���", "��Ÿ"};
	private JComboBox<String> comboBox;
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
		
		add(panelBtnPrevious, BorderLayout.NORTH);
		add(panelWrite, BorderLayout.CENTER);
		add(panelBtn, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void makeBtnPrevious() {
		panelBtnPrevious = new JPanel();
		
		btnPrevious = new JButton(new ImageIcon("images/previous.png"));
		btnPrevious.setBackground(new Color(0xF4F3EF));
		btnPrevious.setBorderPainted(false);
		btnPrevious.setContentAreaFilled(false);
		btnPrevious.addActionListener(this);
		
		panelBtnPrevious.add(btnPrevious, BorderLayout.WEST);
	}

	private void makeWrite() {
		panelWrite = new JPanel();
		panelWrite.setLayout(new GridLayout(7,1,10,10));
		
		JLabel labelFoodName = new JLabel("���� �̸��� �����ΰ���?");
		textFieldFoodName = new JTextField(30);

		comboBox = new JComboBox<String>(type);
		
		JLabel labelIngredient = new JLabel("�ʿ��� ���� �����ΰ���?");
		textFieldIngredient = new JTextField(30);
		
		JLabel labelRecipe = new JLabel("������ : ");
		textAreaRecipe = new JTextArea(7, 20);
		scrollPane = new JScrollPane(textAreaRecipe);
		
		panelBtn = new JPanel();
		
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
		panelWrite.add(comboBox);
		panelWrite.add(labelIngredient);
		panelWrite.add(textFieldIngredient);
		panelWrite.add(labelRecipe);
		panelWrite.add(scrollPane);
		
		panelBtn.add(btnCancel);
		panelBtn.add(btnWrite);
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
			
			String sql = "INSERT INTO BBAN.'write'"
					+ "('index', FOODNAME, INGREDIENT, RECIPE)"
					+ "VALUES(TEMP.NEXTVAL, '" + foodName +"', '" + ingredient + "', '" + recipe +"')";
			System.out.println(sql);
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						"bban",
						"1111");
				stmt  = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				
				JOptionPane.showMessageDialog(null, "�ۼ��Ϸ�", "", JOptionPane.PLAIN_MESSAGE);				
				
				
				//�ʿ���� �κ� ������ ��񾵰��� ��
//				this.setVisible(false);
//				new Read(foodName, ingredient, recipe);
				
			} catch (SQLException e1) {
				System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
				e1.printStackTrace();
			} finally {
				try {
					result.close();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if(obj == btnCancel) {
			JOptionPane.showConfirmDialog(null, "�ۼ��� �׸��ϰڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
			
			
		} else if(obj == btnPrevious) {
			
			this.setVisible(false);
			new Menu();
		}
	}

}
