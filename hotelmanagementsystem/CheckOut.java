package com.hotelmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class CheckOut extends JFrame implements ActionListener {
    private Choice c1;
    private JTextField t1;
    private JButton checkOutButton, backButton, l2;

    public CheckOut() {
        // Set up frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(530, 200, 800, 294);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel imgLabel = new JLabel(i2);
        imgLabel.setBounds(300, 0, 500, 225);
        add(imgLabel);

        // Labels and text fields
        JLabel lblCheckOut = new JLabel("Check Out");
        lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCheckOut.setBounds(70, 11, 140, 35);
        add(lblCheckOut);

        JLabel lblNumber = new JLabel("Number:");
        lblNumber.setBounds(20, 85, 80, 14);
        add(lblNumber);

        c1 = new Choice();
        c1.setBounds(130, 82, 150, 20);
        add(c1);

        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(20, 132, 86, 20);
        add(lblRoomNumber);

        t1 = new JTextField();
        t1.setBounds(130, 132, 150, 20);
        add(t1);

        // Buttons
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        l2 = new JButton(i6);
        l2.setBounds(290, 82, 20, 20);
        l2.addActionListener(this);
        add(l2);

        checkOutButton = new JButton("Check Out");
        checkOutButton.setBounds(50, 200, 100, 25);
        checkOutButton.setBackground(Color.BLACK);
        checkOutButton.setForeground(Color.WHITE);
        checkOutButton.addActionListener(this);
        add(checkOutButton);

        backButton = new JButton("Back");
        backButton.setBounds(160, 200, 100, 25);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        // Populate choice from database
        try {
            Connectivity c = new Connectivity();
            ResultSet rs = c.smtStatement.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == l2) {
            try {
                Connectivity c = new Connectivity();
                String number = c1.getSelectedItem();
                ResultSet rs = c.smtStatement.executeQuery("select * from customer where number = '" + number + "'");

                if (rs.next()) {
                    t1.setText(rs.getString("rno"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == checkOutButton) {
            String id = c1.getSelectedItem();
            String s1 = t1.getText();
            String deleteSQL = "Delete from customer where number = '" + id + "'";
            String q2 = "update room_details set availability = 'Available' where rno= '" + s1 + "'";

            try {
                Connectivity c = new Connectivity();
                c.smtStatement.executeUpdate(deleteSQL);
                c.smtStatement.executeUpdate(q2);
                JOptionPane.showMessageDialog(null, "Check Out Successful");
                setVisible(false);
                new Reception().setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Reception().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
