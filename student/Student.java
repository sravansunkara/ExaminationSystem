package student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.Login;

public class Student extends JFrame implements ActionListener {
    JButton viewMarks, viewTimeTable, viewPaper;
    
    public Student(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JTextField namefield =  Login.nameField;
        String name = namefield.getText();
//        String name = "sravan";
        
        JTextField passwordfield =  Login.passwordField;
        String regno = passwordfield.getText();
//        String regno = "12007920";
        
        JLabel nameLabel = new JLabel(name.toUpperCase());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        
        JLabel regnoLabel = new JLabel(regno);
        regnoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        regnoLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(regnoLabel, gbc);
                
        viewMarks = new JButton("View Marks");
        viewMarks.setFont(new Font("Arial", Font.BOLD, 12));
        viewMarks.setBackground(Color.BLUE);
        viewMarks.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        viewMarks.setPreferredSize(new Dimension(100, 50));
        viewMarks.addActionListener(this);
        add(viewMarks, gbc);
        
        viewTimeTable = new JButton("<html><center>View<br>TimeTable</center></html>");
        viewTimeTable.setFont(new Font("Arial", Font.BOLD, 12));
        viewTimeTable.setBackground(Color.BLUE);
        viewTimeTable.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        viewTimeTable.setPreferredSize(new Dimension(100, 50));
        viewTimeTable.addActionListener(this);
        add(viewTimeTable, gbc);
        
        viewPaper = new JButton("View Paper");
        viewPaper.setFont(new Font("Arial", Font.BOLD, 12));
        viewPaper.setBackground(Color.BLUE);
        viewPaper.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        viewPaper.setPreferredSize(new Dimension(100, 50));
        viewPaper.addActionListener(this);
        add(viewPaper, gbc);
        
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        String message = e.getActionCommand();
        
        if(message.equals("View Marks")){
            new ViewMarks();
        }else if(message.equals("<html><center>View<br>TimeTable</center></html>")){
            new ViewTimeTable();
        }else if(message.equals("View Paper")){
            new ViewPaper();
        }
        
    }
    
    public static void main(String[] args){
        new Student();
    }
    
}
