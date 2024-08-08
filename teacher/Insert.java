package teacher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Insert extends JFrame implements ActionListener {
    JButton submit;
    JTextField nameField, regnoField, mathsField, physicsField, chemistryField;
    
    public Insert(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("INSERT STUDENT DETAILS");
        heading.setFont(new Font("Arial", Font.BOLD, 14));
        heading.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heading, gbc);
        
        JLabel name = new JLabel("Name:");
        name.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(name, gbc);
        
        nameField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(nameField, gbc);
        
        JLabel regno = new JLabel("Registration:");
        regno.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(regno, gbc);
        
        regnoField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(regnoField, gbc);
        
        JLabel maths = new JLabel("Maths:");
        maths.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(maths, gbc);
        
        mathsField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(mathsField, gbc);
        
        JLabel physics = new JLabel("Physics:");
        physics.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(physics, gbc);
        
        physicsField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(physicsField, gbc);
        
        JLabel chemistry = new JLabel("Chemistry:");
        chemistry.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(chemistry, gbc);
        
        chemistryField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(chemistryField, gbc);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 7;
        submit.setPreferredSize(new Dimension(100, 20));
        submit.addActionListener(this);
        add(submit, gbc);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void actionPerformed(ActionEvent e){
        String name = nameField.getText();
        String regno = regnoField.getText();
        String mathsStr = mathsField.getText();
        String physicsStr = physicsField.getText();
        String chemistryStr = chemistryField.getText();
        
        Double maths = Double.valueOf(mathsStr);
        Double physics = Double.valueOf(physicsStr);
        Double chemistry = Double.valueOf(chemistryStr);
        Double total = maths+physics+chemistry;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO STUDENTS VALUES(?,?,?,?,?,?)");
            
            statement.setString(1, name);
            statement.setString(2, regno);
            statement.setDouble(3, maths);
            statement.setDouble(4, physics);
            statement.setDouble(5, chemistry);
            statement.setDouble(6, total);
            
            int i = statement.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Student details inserted successfully");
                nameField.setText("");
                regnoField.setText("");
                mathsField.setText("");
                physicsField.setText("");
                chemistryField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            connection.close();
            
        } catch (Exception insertEx){
            System.out.println(insertEx);
        }
        
        
    }
    
    public static void main(String[] args){
        new Insert();
    }
}
