package teacher;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.nio.file.*;

public class UploadPaper extends JFrame implements ActionListener {
    JButton upload;
    JComboBox name;
    JTextArea textArea;
    
    public UploadPaper(){
        setSize(450, 450);
        getContentPane().setBackground(Color.getHSBColor(10, 24, 73));
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 10, 100, 20);
        nameLabel.setForeground(Color.BLUE);
        add(nameLabel);

        String[] exams = {"Maths", "Physics", "Chemistry"};

        name = new JComboBox(exams);
        name.setBounds(100, 10, 100, 20);
        add(name);

        upload = new JButton("Upload");
        upload.setBounds(100, 40, 100, 20);
        upload.setFont(new Font("Arial", Font.BOLD, 14));
        upload.setBackground(Color.BLUE);
        upload.setForeground(Color.WHITE);
        upload.addActionListener(this);
        add(upload);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 70, 320, 320);
        add(scrollPane);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
    public void actionPerformed(ActionEvent e){
        String selectedExam = "";
        if (e.getSource() == upload) {
            selectedExam = (String) name.getSelectedItem();
        }
        
        try{
            Path p = Paths.get("Papers/" + selectedExam + ".txt");
            if(Files.exists(p)){
                JOptionPane.showMessageDialog(null, "File already Created");
            }else{
                Path donePath = Files.createFile(p);
                
                String content = textArea.getText();
                Files.write(donePath, content.getBytes());

                JOptionPane.showMessageDialog(null, "File uploaded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception f){
            System.out.println(f);
        }
    }
    
    public static void main(String[] args){
        new UploadPaper();
    }
}
