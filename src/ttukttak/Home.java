package ttukttak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
   
   private Image background = new ImageIcon("images/background.jpg").getImage();
   private JButton btnStart;
   private Font font = new Font("ī��24 �����", 0, 30);

   public Home(){
      setTitle("Ȩ");
      setSize(1024, 682);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //
      JPanel panelBase = new JPanel();
      panelBase.setLayout(null);
      
      // �����ϱ� ��ư
      btnStart = new JButton("�����ϱ�");
      btnStart.setFont(font);
      btnStart.setForeground(Color.orange);
      btnStart.setBackground(new Color(255, 232, 120));
      btnStart.setBounds(290, 380, 200, 60);
      btnStart.setBorderPainted(false);
      btnStart.setFocusPainted(false);
      btnStart.addActionListener(this);
         
      panelBase.add(btnStart);
      add(panelBase);

      setVisible(true);
   }

   public void paint(Graphics g) {
      g.drawImage(background, 0, 0, null);
   }

   public static void main(String[] args) {
      new Home();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();

      // �����ϱ� ��ư�� ������, �α���
      if(obj == btnStart) {
         new Login();
      }
   }

}
