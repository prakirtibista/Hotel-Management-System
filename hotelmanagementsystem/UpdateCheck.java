package com.hotelmanagementsystem;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
public class UpdateCheck extends JFrame implements ActionListener{
	Choice c1;// used when u need value from the database
	JTextField roomField,nameField,paidField,pendingField,checkinField;
	JButton check,back,update;
	UpdateCheck(){
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(300,200,900,500);
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
		Image i2= i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel img = new JLabel(i3);
		img.setBounds(400,50,400,400);
		add(img);
		
		JLabel textJLabel=new JLabel("Update Status");
		add(textJLabel);
		textJLabel.setFont(new Font("Serif",Font.BOLD,25));
		textJLabel.setForeground(Color.BLUE);
		textJLabel.setBounds(90,20,200,30);
		
		JLabel cid=new JLabel("Customer ID");
		cid.setBounds(30,80,100,20);
		add(cid);
		
		JLabel rno=new JLabel("Room Number");
		add(rno);
		rno.setBounds(30,120,100,20);
		
		roomField= new JTextField();
		roomField.setBounds(200,120,150,25);
		add(roomField);
		
		JLabel name=new JLabel("Name");
		add(name);
		name.setBounds(30,160,100,20);
		
		nameField= new JTextField();
		nameField.setBounds(200,160,150,25);
		add(nameField);
		
		JLabel checkin=new JLabel("Checkin ");
		add(checkin);
		checkin.setBounds(30,200,100,20);
		
		checkinField= new JTextField();
		checkinField.setBounds(200,200,150,25);
		add(checkinField);
		
		JLabel paid=new JLabel("Paid");
		add(paid);
		paid.setBounds(30,240,100,20);
		
		paidField= new JTextField();
		paidField.setBounds(200,240,150,25);
		add(paidField);
		
		JLabel pending=new JLabel("Pending");
		add(pending);
		pending.setBounds(30,280,100,20);
		
		pendingField= new JTextField();
		pendingField.setBounds(200,280,150,25);
		add(pendingField);
		
		check = new JButton("Check");
		check.setBounds(40,340,70,20);
		check.setBackground(Color.BLACK);
		check.setForeground(Color.WHITE);
		check.addActionListener(this);
		add(check);
		
		update=new JButton("Update");
		update.setBounds(120,340,80,20);
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		update.addActionListener(this);
		add(update);
		
		back=new JButton("Back");
		back.setBounds(210,340,80,20);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		add(back);
		
		c1= new Choice();
		c1.setBounds(200,80,150,20);
		add(c1);
		
		try {
			Connectivity connectivity= new Connectivity();
			ResultSet resultSet = connectivity.smtStatement.executeQuery("select * from customer");
			while(resultSet.next()) {
				c1.add(resultSet.getString("number"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==check) {
			String id= c1.getSelectedItem();
			String query="Select * from customer where number='"+id+"'";
			try {
				Connectivity connectivity=new Connectivity();
				ResultSet rSet=connectivity.smtStatement.executeQuery(query);
				while(rSet.next()) {
					roomField.setText(rSet.getString("room"));
					paidField.setText(rSet.getString("deposit"));
					nameField.setText(rSet.getString("name"));
					checkinField.setText(rSet.getString("checkintime"));
				}
				
				ResultSet rSet2= connectivity.smtStatement.executeQuery("select * from room_details where rno ='"+roomField.getText()+ "'");
				while(rSet2.next()) {
					String price = rSet2.getString("price");
					int amount= Integer.parseInt(price)- Integer.parseInt(paidField.getText());
					pendingField.setText(""+amount);
				}
					
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(ae.getSource()==update){
			String number= c1.getSelectedItem();
			String room = roomField.getText();
			String name =nameField.getText();
			String checkin = checkinField.getText();
			String deposit = paidField.getText();
			try{
				Connectivity connectivity= new Connectivity();
				connectivity.smtStatement.executeUpdate("Update customer set name='"+name+"',room='"+room+"',checkintime='"+checkin+"',deposit='"+deposit+"' where number='"+number+"'");
				JOptionPane.showMessageDialog(null,"Data updated successfully!");
				setVisible(false);
				new Reception();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		
		else {
			setVisible(false);
			new Reception();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UpdateCheck();
	}

}
