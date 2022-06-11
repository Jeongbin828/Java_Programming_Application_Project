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
	private JScrollPane scrollPane;
	private JScrollBar scrollBar;
	private JScrollPane scroll;
	private JPanel panelPick;
	
	public Menu(String user_id) {
		
		this.user_id = user_id;
		
		System.out.println(user_id);
		
		setSize(1024, 682);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		
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
		panelBtnWrite.setBackground(Color.WHITE);
		
		btnWrite = new RoundedButton("글쓰기");
		btnWrite.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnWrite.addActionListener(this);
		
		panelBtnWrite.add(btnWrite, BorderLayout.EAST);
	}

	private void makeHeader() {
		panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		panelHeader.setLayout(new BorderLayout());
		
		ImageIcon logo = new ImageIcon("images/logo.jpg");
		JLabel label = new JLabel(logo);
		
		panelHeader.add(label, BorderLayout.CENTER);
	}

	private void makeMenu() {
		
		//
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("", Font.BOLD, 15));

		//
		panelAll = new JPanel();
		panelAll.setBackground(Color.WHITE);
		tabbedPane.addTab("전체", new ImageIcon(""), panelAll);
		
		//
		panelKorean = new JPanel();
		panelKorean.setBackground(Color.WHITE);
		tabbedPane.addTab("한식", new ImageIcon(""), panelKorean);
		
		//
		panelJapanese = new JPanel();
		panelJapanese.setBackground(Color.WHITE);
		tabbedPane.addTab("일식", new ImageIcon(""), panelJapanese);
		
		//
		panelWestern = new JPanel();
		panelWestern.setBackground(Color.WHITE);
		tabbedPane.addTab("양식", new ImageIcon("images/western.png"), panelWestern);
		
		//
		panelEtc = new JPanel();
		panelEtc.setBackground(Color.WHITE);
		tabbedPane.addTab("기타", new ImageIcon(""), panelEtc);
		
		//
		panelPick = new JPanel();
		panelPick.setBackground(Color.WHITE);
		tabbedPane.addTab("찜", new ImageIcon(""), panelPick);
		
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
				btnList.setPreferredSize(new Dimension(500, 150));
				btnList.setLayout(null);
				btnList.setContentAreaFilled(false);
				btnList.addActionListener(this);
				btnList.addMouseListener(this);

				//
				
				
				labelindex = new JLabel(recipeIndex);
				
				// 이미지
				JLabel labelImage = new JLabel();
				labelImage.setBounds(30, 20, 110, 120);
				
				//
				JLabel foodname = new JLabel(foodName);
				foodname.setBounds(25, 15, 300, 30);
				
				JLabel ing = new JLabel(ingredient);
				ing.setBounds(25, 50, 1000, 30);
				
				image = new ImageIcon("images/save_empty.png");
				btnPick = new JButton(image);
				btnPick.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 15));
				btnPick.addActionListener(this);
				btnPick.addMouseListener(this);
				
				btnList.add(labelindex);
				btnList.add(labelImage);
				btnList.add(foodname);
				btnList.add(ing);
				btnList.add(btnPick);
				
				panelAll.add(btnList);
				labelindex.setVisible(false);
				index = labelindex.getText();
				System.out.println(index);
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
			new Write(user_id);
		} else if(obj == btnList) {
			System.out.println("리스트를 선택함");
		} else if(obj == btnPick) {
			System.out.println("찜하기 버튼을 선택함");
			
			
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
						System.out.println("찜한 버튼이 취소 되었습니다.");
					}else if(save.equals("0")){
						sql = "update pick set save = 1 where user_id = '" + user_id + "' and recipe_index = '" + index + "'";
						stmt = conn.createStatement();
						int result = stmt.executeUpdate(sql);
						System.out.println("취소했던 요리가 다시 찜하기 되었습니다.");
					}
				}else {
					sql = "insert into pick(user_id, recipe_index, save) values('" + user_id + "', '" + index + "', 1";
					stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					System.out.println("새로 찜한 요리에 추가되었습니다.");
				}
				
//				if(!(result.next())) {
//					sql = "insert into pick(user_id, recipe_index, save) values('" + user_id + "', '" + index + "', 1)";
//					
//					stmt = conn.createStatement();
//				
//					int result = stmt.executeUpdate(sql);
//					System.out.println("에라모르겠다");
//					
//				}else {
//					String save = result.getString("save");
//					if(save.equals("1")) {
//						sql = "update pick set save = 0 where user_id = '" + user_id + "' and recipe_index = '" + index + "'";
//						stmt = conn.createStatement();
//						int result = stmt.executeUpdate(sql);
//						System.out.println(sql);
//					}else {
//						sql = "update pick set save = 1 where user_id = '" + user_id + "' and recipe_index = '" + index + "'";
//						stmt = conn.createStatement();
//						int result = stmt.executeUpdate(sql);
//						System.out.println(sql);
//					}
//					

				
//				//				while(result.next()) {
//					
//				}
//			
			}catch (SQLException e1) {
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
		
			
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnList) {
			System.out.println("리스트 버튼 선택함");
		} else if(obj == btnPick) {
			System.out.println("찜하기 버튼 선택함");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
