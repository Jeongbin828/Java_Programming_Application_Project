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

public class Menu extends JFrame implements ActionListener, MouseListener{
	
	private JPanel panelHeader;
	private JToolBar toolBar;
	private JButton btnUser, btnSave;
	private JTabbedPane tabbedPane;
	private JPanel panelAll, panelKorean, panelJapanese, panelWestern, panelEtc;
	private JPanel panelBtnWrite;
	private JButton btnWrite;
//	private Login U_data;
	private Login login_id;
	private String user_id;
	
	private Connection conn = null;
	private Statement stmt = null;
	
	public Menu(Login login) {
//		U_data = login;
		
		login_id = login;
		user_id = login_id.getUser_id();
		
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
	
	private void makeBtnWrite() {
		panelBtnWrite = new JPanel();
		
		btnWrite = new JButton("글쓰기");
		btnWrite.addActionListener(this);
		
		panelBtnWrite.add(btnWrite);
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
		
//		tabbedPane.setVerticalTextPosition(JB.BOTTOM);
//		btnWestern.setHorizontalTextPosition(JButton.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnWrite) {
			
			this.setVisible(false);
//			user_id = login_id.getUser_id();
			new Write(user_id);
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
