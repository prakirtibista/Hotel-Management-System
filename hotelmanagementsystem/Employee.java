package com.hotelmanagementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class Employee extends JFrame implements ActionListener {
    JButton back;

    Employee() {
        setTitle("Employee Details");
        setSize(1050, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        
        JPanel headerPanel = new JPanel(new GridLayout(1, 8));
        JTable table = new JTable();
        JScrollPane sPane = new JScrollPane(table);
        add(headerPanel, BorderLayout.NORTH);
        add(sPane, BorderLayout.CENTER);

        try {
            Connectivity connectivity = new Connectivity();
            ResultSet resultSet = connectivity.smtStatement.executeQuery("Select * from empdetails");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setPreferredSize(new Dimension(100, 30));
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            new Reception();
            setVisible(false); // Close current frame
        }
    }

    public static void main(String[] args) {
        new Employee();
    }
}
