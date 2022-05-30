package ttukttak;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class List extends JFrame{

   private Panel panelImg;
   private Panel panelFood;
   private JLabel image;
   private JButton pick;
   private Panel panelPick;
   
   public List() {
      
      setSize(500, 200);
      setLocationRelativeTo(null);
      setResizable(false);
      
      makeList();
      
      add(panelImg, BorderLayout.WEST);
      add(panelFood, BorderLayout.CENTER);
      add(panelPick, BorderLayout.EAST);
      
      setVisible(true);
   }

   private void makeList() {
      
      panelImg = new Panel();
      panelImg.setLayout(null);
      
      image = new JLabel(new ImageIcon("images/jjanggu.jpg"));  //�ӽ�..
      image.setBounds(30, 20, 110, 120);
      image.setBorder(new LineBorder(Color.ORANGE, 4));
      panelImg.setPreferredSize(new Dimension(150, 0));  //�г� ũ�� ����
      
      
      panelFood = new Panel();
      panelFood.setLayout(null);
      
      JLabel foodname = new JLabel("�����");
      foodname.setBounds(25, 15, 300, 30);
      foodname.setFont(new Font("���� ���", Font.BOLD, 20));
   
      JLabel ingredient = new JLabel("��, ��� ...");
      ingredient.setBounds(25, 50, 1000, 30);
      ingredient.setFont(new Font(null, Font.BOLD, 12));
      
      
      panelPick = new Panel();
      panelPick.setLayout(new BorderLayout());
      
      pick = new JButton("��");
      pick.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 15)); // ���� �ֱ�
      pick.setBorderPainted(false);
      pick.setContentAreaFilled(false);
      
      panelImg.add(image, BorderLayout.CENTER);
      panelFood.add(foodname);
      panelFood.add(ingredient);
      panelPick.add(pick, BorderLayout.SOUTH);
      
   }

   public static void main(String[] args) {
      new List();
   }

}
