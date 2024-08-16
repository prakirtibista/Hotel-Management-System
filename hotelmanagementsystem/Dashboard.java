package com.hotelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Dashboard extends JFrame implements ActionListener{
	JMenuItem empJMenu,driverJMenu,rooMenu,receptionJMenu;
	Dashboard(){
		setBounds(0,0,1700,1000);
		setLayout(null);
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
		Image i2= i1.getImage().getScaledInstance(1700, 1000,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel i4= new JLabel(i3);
		i4.setBounds(0,0,1700,1000);
		add(i4);
		
		JLabel textJLabel = new JLabel("THE TAJ GROUP WELCOMES YOU!!");
		textJLabel.setBounds(400,80,1000,50);
		textJLabel.setForeground(Color.white);
		textJLabel.setFont(new Font("TAHOMA",Font.BOLD,46));
		i4.add(textJLabel);
		
		JMenuBar mBar=new JMenuBar();
		mBar.setBounds(0,0,1700,30);
		i4.add(mBar);
		
		JMenu HotelBar=new JMenu("Hotel Management");
		mBar.add(HotelBar);
		HotelBar.setForeground(Color.RED);
		
		JMenu AdminBar=new JMenu("Admin");
		mBar.add(AdminBar);
		AdminBar.setForeground(Color.BLUE);
		
		receptionJMenu=new JMenu("Reception");
		empJMenu= new JMenuItem("Add Employee");
		driverJMenu=new JMenuItem("Add Drivers");
		rooMenu=new JMenuItem("Add Rooms");
		rooMenu.addActionListener(this);
		empJMenu.addActionListener(this);
		driverJMenu.addActionListener(this);
		receptionJMenu.addActionListener(this);
		HotelBar.add(receptionJMenu);
		AdminBar.add(empJMenu);
		AdminBar.add(driverJMenu);
		AdminBar.add(rooMenu);

		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Add Rooms")){
			new AddRooms();
		}
		else if(ae.getActionCommand().equals("Add Employee")){
			new AddEmployee();
		}
		else if(ae.getActionCommand().equals("Add Driver")){
			new AddDriver();
		}
		else if(ae.getActionCommand().equals("Reception")) {
			new Reception();
		}
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dashboard();
	}

}
