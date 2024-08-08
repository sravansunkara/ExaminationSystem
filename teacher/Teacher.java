package teacher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Teacher extends JFrame implements ActionListener{
    JButton insert, update, delete, search, uploadPapers, uploadTimeTable; 
    
    public Teacher(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("ADMIN");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heading, gbc);
        
        insert = new JButton("Insert");
        insert.setFont(new Font("Arial", Font.BOLD, 14));
        insert.setBackground(Color.BLUE);
        insert.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        insert.setPreferredSize(new Dimension(100, 50));
        insert.addActionListener(this);
        add(insert, gbc);
        
        
        update = new JButton("Update");
        update.setFont(new Font("Arial", Font.BOLD, 14));
        update.setBackground(Color.BLUE);
        update.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 2;
        update.setPreferredSize(new Dimension(100, 50));
        update.addActionListener(this);
        add(update, gbc);
        
        delete = new JButton("Delete");
        delete.setFont(new Font("Arial", Font.BOLD, 14));
        delete.setBackground(Color.BLUE);
        delete.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        delete.setPreferredSize(new Dimension(100, 50));
        delete.addActionListener(this);
        add(delete, gbc);
        
        search = new JButton("Search");
        search.setFont(new Font("Arial", Font.BOLD, 14));
        search.setBackground(Color.BLUE);
        search.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 3;
        search.setPreferredSize(new Dimension(100, 50));
        search.addActionListener(this);
        add(search, gbc);
        
        uploadPapers = new JButton("<html><center>Upload<br>Paper</center></html>");
        uploadPapers.setFont(new Font("Arial", Font.BOLD, 14));
        uploadPapers.setBackground(Color.BLUE);
        uploadPapers.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        uploadPapers.setPreferredSize(new Dimension(100, 50));
        uploadPapers.addActionListener(this);
        add(uploadPapers, gbc);
        
        uploadTimeTable = new JButton("<html><center>Upload<br>TimeTable</center></html>");
        uploadTimeTable.setFont(new Font("Arial", Font.BOLD, 14));
        uploadTimeTable.setBackground(Color.BLUE);
        uploadTimeTable.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 5;
        uploadTimeTable.setPreferredSize(new Dimension(100, 50));
        uploadTimeTable.addActionListener(this);
        add(uploadTimeTable, gbc);
        
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        String message = e.getActionCommand();
        
        if(message.equals("Insert")){
            new Insert();
        }else if(message.equals("Update")){
            new Update();
        }else if(message.equals("Delete")){
            new Delete();
        }else if(message.equals("Search")){
            new Search();
        }else if(message.equals("<html><center>Upload<br>Paper</center></html>")){
            new UploadPaper();
        }else if(message.equals("<html><center>Upload<br>TimeTable</center></html>")){
            new UploadExamTimeTable();
        }
    }
    
    public static void main(String[] args){
        new Teacher();
    }

}
