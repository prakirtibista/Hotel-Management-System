package com.hotelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
	JTextField username;
	JPasswordField pw;
	JButton loginButton,cancel;
	Login(){
		getContentPane().setBackground(Color.WHITE);// take the entire container
		
		setLayout(null);
		
		JLabel user=new JLabel("Username: ");
		user.setBounds(40,20,100,30);
		add(user);
		
		 username= new JTextField();
		username.setBounds(150,20,150,30);
		username.addActionListener(this);
		add(username);
		
		JLabel password= new JLabel("Password: ");
		password.setBounds(40,80,100,30);
		add(password);
		
		 pw= new JPasswordField();
		pw.setBounds(150,80,150,30);
		pw.addActionListener(this);
		add(pw);
		
		loginButton=new JButton("Login");
		loginButton.setBounds(80,120,70,30);
		loginButton.setBackground(Color.BLACK);
		loginButton.setForeground(Color.WHITE);
		loginButton.addActionListener(this);
		add(loginButton);
		
		cancel=new JButton("cancel");
		cancel.setBounds(190,120,100,30);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
		Image i2= i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);// to scale the image and not crop it
		ImageIcon i3= new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(350,10,200,200);
		add(image);
		setBounds(500,200,600,300);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==loginButton) {
			String userString= username.getText();
			String pwString=pw.getText();
			try {//external entity thats why try catch block
				Connectivity connection=new Connectivity();
				
				String query="select * from login where username='"+userString+"' and passowrd='"+pwString+"'";//data has been inserted in single quotes
				ResultSet rs= connection.smtStatement.executeQuery(query);
				if(rs.next()) {//points to the next line which means the row was valid
					setVisible(false);
					new Dashboard();
					}
				else {
					JOptionPane.showMessageDialog(null,"Invalid Username or Password");
					setVisible(false);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
		else if(ae.getSource()==cancel) {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new Login();
	}
}
