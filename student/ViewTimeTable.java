package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class ViewTimeTable extends JFrame implements ActionListener {
    public ViewTimeTable(){
        setSize(450, 450);
//        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TIMETABLE");
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                Vector<Vector<Object>> data = new Vector<>();
                Vector<String> columnNames = new Vector<>();
                
                columnNames.add("Name");
                columnNames.add("Date");
                columnNames.add("Time");
                columnNames.add("Room No");
                
                do {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("name"));
                    row.add(rs.getString("date"));
                    row.add(rs.getString("time"));
                    row.add(rs.getString("roomNo"));
                    data.add(row);
                } while (rs.next());
                
                DefaultTableModel model = new DefaultTableModel(data, columnNames);

                JTable table = new JTable(model);
                
                add(new JScrollPane(table));
                
            } else {
                JOptionPane.showMessageDialog(null, "No record found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
            
            connection.close();
            
        } catch (Exception ex){
            System.out.println(ex);
        }
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    public static void main(String[] args){
        new ViewTimeTable();
    }
}
