package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.util.List;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ViewPaper extends JFrame implements ActionListener {
    JButton view;
    JComboBox name;
    public ViewPaper(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.BLUE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        
        String[] exams = {"Maths", "Physics", "Chemistry"};
        
        name = new JComboBox(exams); 
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(name, gbc);
        
        view = new JButton("view");
        view.setFont(new Font("Arial", Font.BOLD, 14));
        view.setBackground(Color.BLUE);
        view.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 2;
        view.addActionListener(this);
        add(view, gbc);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        String date = "";
        String selectedExam = "";
        if (e.getSource() == view) {
            selectedExam = (String) name.getSelectedItem();
        }
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "2002");
            
            PreparedStatement statement = connection.prepareStatement("SELECT date FROM TIMETABLE WHERE name=?");
            statement.setString(1, selectedExam);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                date = rs.getString("date");
            }
            connection.close();
        }
        catch(Exception SQLe){
            System.out.println(SQLe);
        }
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = fr.format(currentDate);
        
        if(formattedDate.equals(date)){
            try{
                Path p = Paths.get("Papers/" + selectedExam + ".txt");

                List<String> data = Files.readAllLines(p);

                StringBuilder stringBuilder = new StringBuilder();
                for (String line : data) {
                    stringBuilder.append(line).append("\n");
                }

                JTextArea textArea = new JTextArea(stringBuilder.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(null, scrollPane, "View " + selectedExam + " Paper", JOptionPane.PLAIN_MESSAGE);
            }
            catch(Exception f){
                System.out.println(f);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Exam not available", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public static void main(String[] args){
        new ViewPaper();
    }
}
