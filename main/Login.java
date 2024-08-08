package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import teacher.Teacher;
import student.Student;

public class Login extends JFrame implements ActionListener{
    JButton login;
    public static JTextField nameField, passwordField;
    
    Login(){
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel heading = new JLabel("LOGIN PAGE");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heading, gbc);
        
        JLabel name = new JLabel("Username:");
        name.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(name, gbc);
        
        nameField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(nameField, gbc);

        
        JLabel password = new JLabel("Password:"); 
        password.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(password, gbc);
        
        passwordField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(passwordField, gbc);
        
        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 4;
        login.addActionListener(this);
        add(login, gbc);
        
        
        setSize(450, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        String userName = nameField.getText();
        String password = passwordField.getText();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            PreparedStatement teacherStatement = connection.prepareStatement("SELECT * FROM teacher WHERE name=? AND password=?");
            teacherStatement.setString(1, userName);
            teacherStatement.setString(2, password);
            ResultSet teacherResult = teacherStatement.executeQuery();
            if(teacherResult.next()){
                setVisible(false);
                new Teacher();
            }else{
                PreparedStatement studentStatement = connection.prepareStatement("SELECT * FROM students WHERE name=? AND regno=?");
                studentStatement.setString(1, userName);
                studentStatement.setString(2, password);

                ResultSet studentResult = studentStatement.executeQuery();
                if(studentResult.next()){
                    setVisible(false);
                    new Student();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
            
            connection.close();
                
        } catch(Exception loginE){
            System.out.println(loginE);
        }
    }
    
    public static void main(String[] args){
        new Login();
    }
    
}
