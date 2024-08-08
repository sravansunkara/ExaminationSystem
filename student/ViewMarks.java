package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import main.Login;

public class ViewMarks extends JFrame implements ActionListener {
    
    public ViewMarks(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        JTextField textField = Login.passwordField;
        String regno = textField.getText();
//        String regno = "12007920";
        
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("YOUR MARKS DISTRIBUTION");
        heading.setFont(new Font("Arial", Font.BOLD, 15));
        heading.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heading, gbc);
        
        double mathsMarks = 0;
        JLabel mathsLabel = new JLabel("Maths");
        mathsLabel.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(mathsLabel, gbc);
        
        JProgressBar mathsBar = new JProgressBar(0, 100);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(mathsBar, gbc);
        
        double physicsMarks = 0;
        JLabel physicsLabel = new JLabel("Physics");
        physicsLabel.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(physicsLabel, gbc);
        
        JProgressBar physicsBar = new JProgressBar(0, 100);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(physicsBar, gbc);
        
        double chemistryMarks = 0;
        JLabel chemistryLabel = new JLabel("Chemistry");
        chemistryLabel.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(chemistryLabel, gbc);
        
        JProgressBar chemistryBar = new JProgressBar(0, 100);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(chemistryBar, gbc);
        
        double totalMarks = 0;
        JLabel totalLabel = new JLabel("Total");
        totalLabel.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(totalLabel, gbc);
        
        JProgressBar totalBar = new JProgressBar(0, 300);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(totalBar, gbc);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE regno=?");
            statement.setString(1, regno);
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                mathsMarks = rs.getDouble("maths");
                physicsMarks = rs.getDouble("physics");
                chemistryMarks = rs.getDouble("chemistry");
                totalMarks = rs.getDouble("total");
            }
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
        
        mathsBar.setValue((int)mathsMarks);
        mathsBar.setStringPainted(true);
        
        physicsBar.setValue((int)physicsMarks);
        physicsBar.setStringPainted(true);
        
        chemistryBar.setValue((int)chemistryMarks);
        chemistryBar.setStringPainted(true);
        
        totalBar.setValue((int)totalMarks);
        totalBar.setStringPainted(true);
        
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    public static void main(String[] args){
        new ViewMarks();
    }
}
