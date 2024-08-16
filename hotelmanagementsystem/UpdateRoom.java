package com.hotelmanagementsystem;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateRoom extends JFrame implements ActionListener{
	JTextField roomField,availabilityField,statusField;
	JButton back,update,check;
	Choice c1;
	public UpdateRoom() {
		// TODO Auto-generated constructor stub
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(300,200,900,500);
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
		Image i2= i1.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel img = new JLabel(i3);
		img.setBounds(400,50,400,400);
		add(img);
		
		JLabel textJLabel=new JLabel("Update Room Status");
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
		
		JLabel availability=new JLabel("Availability");
		add(availability);
		availability.setBounds(30,160,100,20);
		
		availabilityField= new JTextField();
		availabilityField.setBounds(200,160,150,25);
		add(availabilityField);
		
		JLabel status=new JLabel("Cleaning Status");
		add(status);
		status.setBounds(30,200,100,20);
		
		statusField= new JTextField();
		statusField.setBounds(200,200,150,25);
		add(statusField);
		
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
				}
				ResultSet resultSet = connectivity.smtStatement.executeQuery("Select * from room_details where rno='"+roomField.getText()+"'");
				while(resultSet.next()) {
					availabilityField.setText(resultSet.getString("availability"));
					statusField.setText(resultSet.getString("status"));
				}
				
					
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(ae.getSource()==update){
			String number= c1.getSelectedItem();
			String room = roomField.getText();
			String avail =availabilityField.getText();
			String status = statusField.getText();
			try{
				Connectivity connectivity= new Connectivity();
				connectivity.smtStatement.executeUpdate("Update room_details set availability='"+avail+"',status='"+status+"' where rno='"+room+"'");
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
		new UpdateRoom();
	}

}
