package com.hotelmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connectivity {
	Connection connection;
	Statement smtStatement;
	public Connectivity() {
		// TODO Auto-generated constructor stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_mgmt","root","cbpp");
		smtStatement=connection.createStatement();
	}catch(Exception e) {
		e.printStackTrace();
	}

}
}
