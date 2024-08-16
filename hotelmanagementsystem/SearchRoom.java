package com.hotelmanagementsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.beancontext.BeanContextServiceAvailableEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.sql.*;

public class SearchRoom extends JFrame implements ActionListener  {
	JComboBox roomBox;
	JButton submit;
	JTable table;
	JCheckBox availableBox;
	JButton back = new JButton();
	SearchRoom(){
		 setTitle("Room Search");
	        setBounds(0, 0, 800, 600); // Adjusted window size
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());
	        
	        JPanel topPanel = new JPanel();
	        JLabel roomJLabel = new JLabel("Room Type:");
	        topPanel.add(roomJLabel);

	        String room[] = { "Single Bed", "Double Bed" };
	        roomBox = new JComboBox(room);
	        topPanel.add(roomBox);
	        add(topPanel, BorderLayout.NORTH);
		
		JPanel panel= new JPanel();
		table = new JTable();
		JScrollPane scrollPane= new JScrollPane(table);
		add(scrollPane,BorderLayout.CENTER);
		try {
		Connectivity connectivity= new Connectivity();
		ResultSet rSet = connectivity.smtStatement.executeQuery("Select * from room_details");
		table.setModel(DbUtils.resultSetToTableModel((rSet)));	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		availableBox = new JCheckBox("Only Display Available");
		availableBox.addActionListener(this);
		
	    back = new JButton("Back");
        back.setPreferredSize(new Dimension(100, 30));
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
		
		JPanel panel2= new JPanel();
		submit=new JButton("Submit");
		submit.setBounds(60,470,80,30);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		submit.setPreferredSize(new Dimension(100, 30));
		panel2.add(submit);
		panel2.add(back);
		panel2.add(availableBox);
		add(panel2,BorderLayout.SOUTH);
		submit.addActionListener(this);
		
		setVisible(true);
		}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==submit) {
			try {
				Connectivity connectivity= new Connectivity();
				String q1="Select * from room_details where bedtype='"+(String) roomBox.getSelectedItem()+"'";
				String q2 = "Select * from room_details where availability = 'available' and bedtype='"+(String) roomBox.getSelectedItem()+"'";
				if(availableBox.isSelected()) {
					ResultSet rSet = connectivity.smtStatement.executeQuery(q2);
					table.setModel(DbUtils.resultSetToTableModel((rSet)));
					
				}
				else {
					ResultSet rSet = connectivity.smtStatement.executeQuery(q1);
					table.setModel(DbUtils.resultSetToTableModel((rSet)));
				}
				
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		if(ae.getSource()==back) {
			new Reception();
			setVisible(false);
		}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SearchRoom();
	}

}
