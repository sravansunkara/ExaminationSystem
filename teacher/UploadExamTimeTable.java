package teacher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UploadExamTimeTable extends JFrame implements ActionListener {
    JButton submit;
    JTextField nameField, dateField, timeField, roomNoField;
    
    public UploadExamTimeTable(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("UPLOAD EXAM TIMETABLE");
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
        
        JLabel date = new JLabel("Date:");
        date.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(date, gbc);
        
        dateField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(dateField, gbc);
        
        JLabel time = new JLabel("Time:");
        time.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(time, gbc);
        
        timeField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(timeField, gbc);
        
        JLabel roomNo = new JLabel("roomNo:");
        roomNo.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(roomNo, gbc);
        
        roomNoField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(roomNoField, gbc);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 6;
        submit.setPreferredSize(new Dimension(100, 20));
        submit.addActionListener(this);
        add(submit, gbc);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        String name = nameField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String roomNo = roomNoField.getText();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TIMETABLE VALUES(?,?,?,?)");
            
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, time);
            statement.setString(4, roomNo);
            
            int i = statement.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "TimeTable inserted successfully");
                nameField.setText("");
                dateField.setText("");
                timeField.setText("");
                roomNoField.setText("");
                
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert TimeTable", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            connection.close();
            
        } catch (Exception insertEx){
            System.out.println(insertEx);
        }
    }
    
    public static void main(String[] args){
        new UploadExamTimeTable();
    }
}
