package ttukttak;

import javax.swing.*;
import javax.swing.border.Border;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.IntStream;

public class Menu extends JFrame implements ActionListener, MouseListener{
	
	private JPanel panelHeader;
	private JToolBar toolBar;
	private JButton btnUser, btnSave;
	private JTabbedPane tabbedPane;
	private JPanel panelAll, panelKorean, panelJapanese, panelWestern, panelEtc;
	private JPanel panelBtnWrite;
	private JButton btnWrite;
	private Login login_id;
	private String user_id;
	
	private Connection conn = null;
	private java.sql.Statement stmt = null;
	private ResultSet result = null;
	private JButton btnList;
	private JButton btnPick;
	private ImageIcon image;
	private JButton buttonSource;
	private String recipeIndex;
	private String index;
	private JLabel labelindex;
	private String sql;
	
//	public Menu(String user_id) {
//		this.user_id = user_id;
//	}
	public Menu(String user_id) {
		
		this.user_id = user_id;
		
		System.out.println(user_id);
		
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(0xF4F3EF));
		
		//
		makeHeader();
		
		//
		makeMenu();
		
		//
		makeBtnWrite();
		
		
		add(panelHeader, BorderLayout.NORTH);
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
		panelBtnWrite.setBackground(new Color(0xF4F3EF));
		
		btnWrite = new JButton("글쓰기");
		btnWrite.addActionListener(this);
		
		panelBtnWrite.add(btnWrite, BorderLayout.EAST);
	}

	private void makeHeader() {
		panelHeader = new JPanel();
		panelHeader.setBackground(new Color(0xF4F3EF));
		panelHeader.setLayout(new BorderLayout());
		
		ImageIcon logo = new ImageIcon("images/logo_basic.jpg");
		JLabel label = new JLabel(logo);
		
		toolBar = new JToolBar();
		toolBar.setBackground(new Color(0xF4F3EF));
		toolBar.setBorderPainted(false);
		
		btnSave = new JButton(new ImageIcon("images/save_fill.png"));
		btnSave.setBackground(new Color(0xF4F3EF));
		btnSave.setBorderPainted(false);
		btnSave.addActionListener(this);
		
		btnUser = new JButton(new ImageIcon("images/user_fill.png"));
		btnUser.setBackground(new Color(0xF4F3EF));
		btnUser.setBorderPainted(false);
		btnUser.addActionListener(this);
		
		toolBar.add(btnSave);
		toolBar.add(btnUser);
		
		panelHeader.add(label, BorderLayout.CENTER);
		panelHeader.add(toolBar, BorderLayout.EAST);
	}

	private void makeMenu() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(0xF4F3EF));
		tabbedPane.setFont(new Font("", Font.BOLD, 15));
		tabbedPane.addMouseListener(this);
		
		panelAll = new JPanel();
		panelAll.setBackground(new Color(0xF4F3EF));
		tabbedPane.addTab("전체", new ImageIcon(""), panelAll);
		
		
		panelKorean = new JPanel();
		panelKorean.setBackground(new Color(0xF4F3EF));
		tabbedPane.addTab("한식", new ImageIcon(), panelKorean);
		
		panelJapanese = new JPanel();
		panelJapanese.setBackground(new Color(0xF4F3EF));
		tabbedPane.addTab("일식", new ImageIcon(""), panelJapanese);
		
		panelWestern = new JPanel();
		panelWestern.setBackground(new Color(0xF4F3EF));
		tabbedPane.addTab("양식", new ImageIcon("images/western.png"), panelWestern);
		
		panelEtc = new JPanel();
		panelEtc.setBackground(new Color(0xF4F3EF));
		tabbedPane.addTab("기타", new ImageIcon(""), panelEtc);
		
		String sql = "select recipe_index, image, foodname, ingredient from recipe";
		
		try {
			System.out.println(sql);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
			System.out.println("연결 성공");
			stmt = conn.createStatement();
		
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
			
				recipeIndex = result.getString("recipe_index");
				String foodName = result.getString("foodname");
				String ingredient = result.getString("ingredient");
				
				btnList = new JButton();
				btnList.setContentAreaFilled(false);
				btnList.addActionListener(this);
				btnList.setLayout(new FlowLayout());

				btnList.setPreferredSize(new Dimension(500, 150));
				
				labelindex = new JLabel(recipeIndex);
				JLabel labelImage = new JLabel();
				JLabel foodname = new JLabel(foodName);
				JLabel ing = new JLabel(ingredient);
				image = new ImageIcon("images/save_empty.png");
				btnPick = new JButton(image);
				btnPick.addActionListener(this);
				
				btnList.add(labelindex);
				btnList.add(labelImage);
				btnList.add(foodname);
				btnList.add(ing);
				btnList.add(btnPick);
				
				panelAll.add(btnList);
				labelindex.setVisible(false);
				System.out.println(btnList.getAccessibleContext().getAccessibleIndexInParent() + 1);
				System.out.println(foodName);
				System.out.println(ingredient);
			}
		} catch (SQLException e1) {
			System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
			e1.printStackTrace();
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnWrite) {
			
			this.setVisible(false);
		
//			user_id = login_id.getUser_id();
			new Write(user_id);
		} else if(obj == btnList) {
			System.out.println(btnList.getAccessibleContext().getAccessibleIndexInParent());
			
		} else if(obj == btnPick) {
			
			index = labelindex.getText();
			
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useUnicode=true&serverTimezone=UTC", "root", "manager");
				
				sql = "select save from pick where user_id = '" + user_id +"' and recipe_index = '" + index + "'";
				stmt = conn.createStatement();
				
				result = stmt.executeQuery(sql);
				System.out.println(sql);
				
				if(result.next()) {
					String save = result.getString("save");
					if(save.equals("1")) {
						sql = "update pick set save = 0 where user_id = '" + user_id + "' and recipe_index = '" + index + "'";
						stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
					}else {
						sql = "update pick set save = 1 where user_id = '" + user_id + "' and recipe_index = '" + index + "'";
						stmt = conn.createStatement();
						result = stmt.executeQuery(sql);
					}
				}else {
//					sql = "insert into pick(user_id, recipe_index, save) values('" + user_id + "', '" + index + "', 1)";
//					
//					stmt = conn.createStatement();
//				
//					int result = stmt.executeUpdate(sql);
					System.out.println("에라모르겠다");
//					

				}
//				//				while(result.next()) {
//					
//				}
//			
			
			} catch (SQLException e1) {
				System.out.println("SQLException 예외 발생 : 접속 정보 확인이 필요합니다.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException 예외 발생 : 해당 드라이버가 없습니다.");
				e1.printStackTrace();
			} finally {
				try {
					result.close();
					stmt.close();
					conn.close();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
//		 else if(obj == btnSave) {
//			this.setVisible(false);
//			
//			new Save();
//		} else if(obj == btnUser) {
//			this.setVisible(false);
//			
//		} 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
