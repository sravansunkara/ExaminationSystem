package teacher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Delete extends JFrame implements ActionListener {
    JButton submit;
    JTextField regnoField;
    
    public Delete(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("DELETE STUDENT DETAILS");
        heading.setFont(new Font("Arial", Font.BOLD, 14));
        heading.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heading, gbc);
        
        JLabel regno = new JLabel("Registration:");
        regno.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(regno, gbc);
        
        regnoField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(regnoField, gbc);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 3;
        submit.setPreferredSize(new Dimension(100, 20));
        submit.addActionListener(this);
        add(submit, gbc);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        String regno = regnoField.getText();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("delete from students WHERE regno=?");
            
            statement.setString(1, regno);
            
            int i = statement.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Student details deleted successfully");
                regnoField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            connection.close();
            
        } catch (Exception insertEx){
            System.out.println(insertEx);
        }
    }
    
    public static void main(String[] args){
        new Delete();
    }
}
