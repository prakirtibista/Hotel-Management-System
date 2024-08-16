package com.hotelmanagementsystem;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class AddEmployee extends JFrame implements ActionListener{
	JTextField name,age,salary,phone,email,aadhar;
	JRadioButton male,female;
	JButton submit;
	JComboBox cBox;
	public AddEmployee() {
		// TODO Auto-generated constructor stub
	
		setLayout(null);
		setBounds(100,100,1100,565);
		getContentPane().setBackground(Color.WHITE);
		JLabel nameJLabel= new JLabel("Name");
		nameJLabel.setBounds(50,70,1000,50);
		add(nameJLabel);
		
		name=new JTextField();
		name.setBounds(150,80,300,25);
		add(name);
		
		JLabel ageJLabel= new JLabel("Age");
		ageJLabel.setBounds(50,120,1000,50);
		add(ageJLabel);
		
		age= new JTextField();
		age.setBounds(150,130,300,25);
		add(age);
		
		JLabel genderJLabel = new JLabel("Gender");
		genderJLabel.setBounds(50,170,1000,50);
		add(genderJLabel);
		
		male= new JRadioButton("Male");
		male.setBounds(150,180,120,25);
		male.setBackground(Color.white);
		add(male);
		
		female= new JRadioButton("Female");
		female.setBounds(300,180,120,25);
		female.setBackground(Color.white);
		add(female);
		
		ButtonGroup bGroup=new ButtonGroup();
		bGroup.add(male);
		bGroup.add(female);
		
		JLabel jobJLabel= new JLabel("Job");
		jobJLabel.setBounds(50,220,1000,50);
		add(jobJLabel);
		
		String jobs[]= {"Porters","Front Desk Clerks","House-Keeping","Chef","Driver","Room Service","Manager"};
		cBox=new JComboBox(jobs);
		cBox.setBounds(150,230,200,30);
		add(cBox);
		
		JLabel salaryLabel= new JLabel("Salary");
		salaryLabel.setBounds(50,270,1000,50);
		add(salaryLabel);
		
		salary= new JTextField();
		salary.setBounds(150,280,300,25);
		add(salary);
		
		JLabel phoneJLabel= new JLabel("Phone");
		phoneJLabel.setBounds(50,320,1000,50);
		add(phoneJLabel);
		
		phone= new JTextField();
		phone.setBounds(150,330,300,25);
		add(phone);
		
		JLabel emailJLabel= new JLabel("Email");
		emailJLabel.setBounds(50,370,1000,50);
		add(emailJLabel);
		
		email= new JTextField();
		email.setBounds(150,380,300,25);
		add(email);
		
		JLabel aadharJLabel= new JLabel("Aadhar");
		aadharJLabel.setBounds(50,420,1000,50);
		add(aadharJLabel);
		
		aadhar=new JTextField();
		aadhar.setBounds(150,430,300,25);
		add(aadhar);
		
		submit=new JButton("Submit");
		submit.setBounds(100,480,100,25);
		submit.setBackground(Color.gray);
		submit.setForeground(Color.white);
		add(submit);
		submit.addActionListener(this);	
		
		JLabel addemp = new JLabel("ADD EMPLOYEE DETAILS");
		addemp.setBounds(550,10,500,100);
		addemp.setFont(new Font("",Font.BOLD,30));
		addemp.setForeground(Color.blue);
		add(addemp);
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
		Image i2=i1.getImage().getScaledInstance(450, 450,Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(520,60,450,380);
		add(image);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String nameString=name.getText();
		String ageString=age.getText();
		String emailString=email.getText();
		String phoneString=phone.getText();
		String salaryString= salary.getText();
		String aadharString=aadhar.getText();
		
		String gender=null;
		if(male.isSelected()) {
			gender= "male";
		}
		else if(female.isSelected()) {
			gender= "female";
		}
		
		if(nameString.equals("")) {
			JOptionPane.showMessageDialog(null,"Name should not be empty");
			return;
		}
		if(ageString.equals("")) {
			JOptionPane.showMessageDialog(null,"Name should not be empty");
			return;
		}
		if(emailString.equals("")) {
			JOptionPane.showMessageDialog(null,"Email should not be empty");
			return;
		}
		 if (!emailString.contains("@")) {
		    JOptionPane.showMessageDialog(null, "Email should contain '@'");
		    return;
		} 
		 if (!(emailString.contains(".com") || emailString.contains(".in"))) {
		    JOptionPane.showMessageDialog(null, "Email should contain a valid domain (e.g., .com or .in)");
		    return;
		}

		if(phoneString.equals("")) {
			JOptionPane.showMessageDialog(null,"Phone should not be empty");
			return;
		}
		if(salaryString.equals("")) {
			JOptionPane.showMessageDialog(null,"Salary should not be empty");
			return;
		}
		if(aadharString.equals("")) {
			JOptionPane.showMessageDialog(null,"Aadhar should not be empty");
			return;
		}
		if(gender==null) {
			JOptionPane.showMessageDialog(null,"Gender should not be empty");
			return;
		}
		String jobString= (String)cBox.getSelectedItem();//returns an object so it needs to be type casted into  string 
		if(jobString.equals("")) {
			JOptionPane.showMessageDialog(null,"Job should not be empty");
			return;
		}
		try {
			Connectivity connectivity= new Connectivity();
			String query= "insert into empdetails values('"+nameString+"','"+ageString+"','"+gender+"','"+jobString+"','"+salaryString+"','"+phoneString+"','"+emailString+"','"+aadharString+"')";
			connectivity.smtStatement.executeUpdate(query);//just inserting not fetching any values
			JOptionPane.showMessageDialog(null, "employee added successfully");
			setVisible(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddEmployee();
	}

}
