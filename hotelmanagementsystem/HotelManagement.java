package com.hotelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HotelManagement extends JFrame implements ActionListener {// either override actionPerformed or make it abstract
	HotelManagement(){//doing all of it in the constructor so that it opens as soon as the code is run
		setSize(1366,565);
		
		setLocation(100,100);//100 from top and 100 from left
		//setBound(100,100,1366,565); //location+size
		
		//setLayout(null); //removes default layout
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
		JLabel image= new JLabel(i1);
		add(image);
		//image.setBounds(0,0,1366,565); -> when frame layout is set to null
		JLabel hms= new JLabel("HOTEL MANAGEMENT SYSTEM");
		hms.setForeground(Color.WHITE); //color class belongs to awt package
		hms.setBounds(20 ,430,1000,50);
		hms.setFont(new Font("serif",Font.BOLD,50));
		image.add(hms);
		
		JButton next = new JButton("next");
		next.setForeground(Color.WHITE);
		next.setBackground(Color.gray);
		next.setFont(new Font("serif",Font.PLAIN,18));
		next.setBounds(1150,450,150,50);
		next.addActionListener(this);
		image.add(next);
		setVisible(true);
		while(true) {
			hms.setVisible(false);
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}
			hms.setVisible(true);
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		new Login();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HotelManagement();
	}

}
