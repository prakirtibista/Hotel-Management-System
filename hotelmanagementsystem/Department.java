package com.hotelmanagementsystem;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.proteanit.sql.DbUtils;

public class Department extends JFrame implements ActionListener {
    JTable table;
    JButton backButton;
    Department() {
        setTitle("Hotel Department Management");
        setSize(1050, 600);
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(1, 5));
        headerPanel.setBackground(Color.white);
        add(headerPanel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        try {
            Connectivity connectivity = new Connectivity();
            ResultSet rs = connectivity.smtStatement.executeQuery("SELECT * FROM department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.setPreferredSize(new Dimension(70, 20));

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(this);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
    	new Reception();
    	setVisible(false);
    }
    public static void main(String[] args) {
        new Department();
    }
}

