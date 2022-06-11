package ttukttak;

import javax.swing.*;
import javax.swing.border.LineBorder;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu extends JFrame implements ActionListener{
	
	private String user_id ;
	private JPanel panelTop, panelBtnWrite;
	private JTabbedPane tabbedPane;
	private JPanel panelAll, panelKorean, panelJapanese, panelWestern, panelEtc;
	private JButton btnWrite;
	
	private JPanel panelImage, panelBase, panelUserId, panelFood;
	private String recipeIndex;
	private JButton btnList;
	private JLabel labelIndex, labelImage, labelUserId, labelFoodName, labelIngredient;
	
	private Connection conn = null;
	private java.sql.Statement stmt = null;
	private ResultSet result = null;

	public Menu(String user_id) {
		this.user_id = user_id;
		
		System.out.println(this.user_id);
		
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		//
		makeTop();
		
		//
		makeMenu();
		
		//
		makeBtnWrite();
		
		add(panelTop, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
		add(panelBtnWrite, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public String getUser_id() {
		return user_id;
	}

	private void makeBtnWrite() {
		panelBtnWrite = new JPanel();
		panelBtnWrite.setLayout(new BorderLayout());
		panelBtnWrite.setBackground(Color.WHITE);
		
		btnWrite = new JButton("�۾���");
		btnWrite.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnWrite.addActionListener(this);
		
		panelBtnWrite.add(btnWrite, BorderLayout.EAST);
	}

	private void makeMenu() {
		
		//
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		
		//
		panelAll = new JPanel();
		panelAll.setBackground(Color.WHITE);
		tabbedPane.addTab("��ü", panelAll);
		
		//
		panelKorean = new JPanel();
		panelKorean.setBackground(Color.WHITE);
		tabbedPane.addTab("�ѽ�", panelKorean);
		
		//
		panelJapanese = new JPanel();
		panelJapanese.setBackground(Color.WHITE);
		tabbedPane.addTab("�Ͻ�", panelJapanese);
		
		//
		panelWestern = new JPanel();
		panelWestern.setBackground(Color.WHITE);
		tabbedPane.addTab("���", panelWestern);
		
		//
		panelEtc = new JPanel();
		panelEtc.setBackground(Color.WHITE);
		tabbedPane.addTab("��Ÿ", panelEtc);
		
		String sql = "select recipe_index, type, image, user_id, foodname, ingredient from recipe";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			//
			while(result.next()) {
				
				//
				recipeIndex = result.getString("recipe_index");
				String writeUserId = result.getString("user_id");
				String foodName = result.getString("foodname");
				String ingredient = result.getString("ingredient");
				
				//
				btnList = new JButton();
				btnList.setLayout(new BorderLayout());
				btnList.setPreferredSize(new Dimension(500, 150));
				btnList.setContentAreaFilled(false);
				btnList.addActionListener(this);
				
				//
				labelIndex = new JLabel(recipeIndex);
				
				// �̹���
				panelImage = new JPanel();
				panelImage.setLayout(null);
				panelImage.setBorder(new LineBorder(Color.BLACK, 4));
				
				labelImage = new JLabel();
				
				//
				panelBase = new JPanel();
				
				// �ۼ��� ���̵�
				panelUserId = new JPanel();
				
				labelUserId = new JLabel("�ۼ��� : " + writeUserId);
				
				panelUserId.add(labelUserId);
				
				// 
				panelFood = new JPanel();
				
				labelFoodName = new JLabel(foodName);
				
				//
				labelIngredient = new JLabel(ingredient);
				
				panelFood.add(labelFoodName, BorderLayout.CENTER);
				panelFood.add(labelIngredient, BorderLayout.SOUTH);
				
				panelBase.add(panelUserId, BorderLayout.NORTH);
				panelBase.add(panelFood, BorderLayout.SOUTH);
				
				btnList.add(panelImage, BorderLayout.WEST);
				btnList.add(panelBase, BorderLayout.EAST);
				
				panelAll.add(btnList);
				
				String type = result.getString("type");
				
				switch(type) {
				case "�ѽ�":
					panelKorean.add(btnList);
					break;
				case "�Ͻ�":
					panelJapanese.add(btnList);
					break;
				case "���":
					panelWestern.add(btnList);
					break;
				default:
					panelEtc.add(btnList);
					break;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("SQLException ���� �߻� : ���� ���� Ȯ���� �ʿ��մϴ�.");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException ���� �߻� : �ش� ����̹��� �����ϴ�.");
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void makeTop() {
		panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		
		ImageIcon logo = new ImageIcon("images/logo.jpg");
		JLabel labelLogo = new JLabel(logo);
		
		panelTop.add(labelLogo);
	}

	public static void main(String[] args) {
	}

	//
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnWrite) {	// �۾��� ��ư�� ������ ��,
			this.setVisible(false);
			new Write(user_id);
		} else if(obj == btnList) {
			new Read(recipeIndex);
		}
	}

}
