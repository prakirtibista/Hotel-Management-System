package com.hotelmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddRooms extends JFrame implements ActionListener {
	JButton submit,cancel;
	JTextField rno,priceField;
	JComboBox availableCB,cleaningstatusCB,btCB;
	AddRooms(){
		setLayout(null);
		setBounds(200,200,1000,600);
		getContentPane().setBackground(Color.white);
		
		JLabel addroomsJLabel=new JLabel("Add Rooms");
		addroomsJLabel.setBounds(150,20,200,100);
		addroomsJLabel.setFont(new Font("",Font.BOLD,30));
		add(addroomsJLabel);
		
		JLabel room = new JLabel("Room No");
		room.setBounds(40,60,150,200);
		room.setFont(new Font("serif",Font.PLAIN,25));
		add(room);
		rno=new JTextField();
		rno.setBounds(240,145,200,30);
		add(rno);
		
		JLabel available=new JLabel("Availability");
		available.setFont(new Font("serif",Font.PLAIN,25));
		available.setBounds(40,120,150,200);
		String a[]= {"available","unavailable"};
		availableCB= new JComboBox(a);
		availableCB.setBounds(240,205,200,30);
		availableCB.setBackground(Color.white);
		add(available);
		add(availableCB);
		
		JLabel cs=new JLabel("Cleaning Status");
		cs.setFont(new Font("serif",Font.PLAIN,25));
		cs.setBounds(40,180,200,200);
		String status[]= {"cleaned","dirty"};
		cleaningstatusCB= new JComboBox(status);
		cleaningstatusCB.setBounds(240,265,200,30);
		cleaningstatusCB.setBackground(Color.white);
		add(cs);
		add(cleaningstatusCB);
		
		JLabel price= new JLabel("Price");
		price.setFont(new Font("serif",Font.PLAIN,25));
		price.setBounds(40,240,150,200);
		add(price);
		priceField=new JTextField();
		priceField.setBounds(240,325,200,30);
		add(priceField);
		
		JLabel bedType= new JLabel("Bed Type: ");
		bedType.setFont(new Font("serif",Font.PLAIN,25));
		bedType.setBounds(40,300,150,200);
		String bed[]= {"Single Bed","Double Bed"};
		btCB= new JComboBox(bed);
		btCB.setBounds(240,385,200,30);
		btCB.setBackground(Color.WHITE);
		add(btCB);
		add(bedType);
		
		submit=new JButton("Add");
		submit.setBounds(60,470,80,30);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		cancel=new JButton("Cancel");
		cancel.setBounds(180,470,80,30);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		add(submit);
		add(cancel);
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
		Image i2=i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel imgJLabel= new JLabel(i3);
		imgJLabel.setBounds(400,50,700,500);
		add(imgJLabel);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==submit) {
		String rnoString=rno.getText();
		String avail= (String)availableCB.getSelectedItem();
		String stat=(String)cleaningstatusCB.getSelectedItem();
		String priceString = priceField.getText();
		String typeString=(String)btCB.getSelectedItem();
		try {
			Connectivity connectivity=new Connectivity();
			String query="insert into room_details values('"+rnoString+"','"+avail+"','"+stat+"','"+priceString+"','"+typeString+"')";
			
			connectivity.smtStatement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "room added successfully");
			setVisible(false);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		else if(ae.getSource()==cancel) {
			setVisible(false);
			new Dashboard();
		}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new AddRooms();
	}

}
