package teacher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class Search extends JFrame implements ActionListener {
    JButton submit;
    JTextField regnoField;
    
    public Search(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel heading = new JLabel("SEARCH STUDENT DETAILS");
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
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE regno=?");
            statement.setString(1, regno);
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                Vector<Vector<Object>> data = new Vector<>();
                Vector<String> columnNames = new Vector<>();
                
                columnNames.add("Name");
                columnNames.add("Registration Number");
                columnNames.add("Maths");
                columnNames.add("Physics");
                columnNames.add("Chemistry");
                columnNames.add("Total");
                
                do {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("name"));
                    row.add(rs.getString("regno"));
                    row.add(rs.getString("maths"));
                    row.add(rs.getString("physics"));
                    row.add(rs.getString("chemistry"));
                    row.add(rs.getString("total"));
                    data.add(row);
                } while (rs.next());
                
                DefaultTableModel model = new DefaultTableModel(data, columnNames);

                JTable table = new JTable(model);

                JOptionPane.showMessageDialog(null, new JScrollPane(table), "Search Result", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No record found for registration number: " + regno, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
            
            connection.close();
            
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args){
        new Search();
    }
}
