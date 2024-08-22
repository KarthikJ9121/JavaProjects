package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Nurses extends JFrame {
    private JLabel nameLabel, ageLabel, genderLabel, phoneLabel, emailLabel, addressLabel, qualificationLabel, Title;
    private JTextField nameField, ageField, genderField, phoneField, emailField, addressField, qualificationField;
    private JButton addButton, nursesInfoButton, backButton;

    public Nurses(AdminLogin admin) {
        setTitle("Nurses");
        getContentPane().setLayout(null);
        getContentPane().setFont(new Font("Bree Serif", Font.BOLD, 13));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setVisible(true);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
        
        Title = new JLabel("Nurses");
        Title.setFont(new Font("Bree Serif", Font.BOLD, 30));
        Title.setForeground(Color.white);
        Title.setBounds(330, 70, 250, 27);
        backgroundLabel.add(Title);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(200, 150, 150, 30);
        backgroundLabel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        nameField.setBounds(400, 150, 250, 30);
        backgroundLabel.add(nameField);

        ageLabel = new JLabel("Age");
        ageLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(200, 200, 150, 30);
        backgroundLabel.add(ageLabel);

        ageField = new JTextField();
        ageField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        ageField.setBounds(400, 200, 250, 30);
        backgroundLabel.add(ageField);

        genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(200, 250, 150, 30);
        backgroundLabel.add(genderLabel);

        genderField = new JTextField();
        genderField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        genderField.setBounds(400, 250, 250, 30);
        backgroundLabel.add(genderField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(200, 300, 150, 30);
        backgroundLabel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        phoneField.setBounds(400, 300, 250, 30);
        backgroundLabel.add(phoneField);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(200, 350, 150, 30);
        backgroundLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        emailField.setBounds(400, 350, 250, 30);
        backgroundLabel.add(emailField);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBounds(200, 400, 150, 30);
        backgroundLabel.add(addressLabel);

        addressField = new JTextField();
        addressField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        addressField.setBounds(400, 400, 250, 30);
        backgroundLabel.add(addressField);

        qualificationLabel = new JLabel("Qualification");
        qualificationLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        qualificationLabel.setForeground(Color.WHITE);
        qualificationLabel.setBounds(200, 450, 150, 30);
        backgroundLabel.add(qualificationLabel);

        qualificationField = new JTextField();
        qualificationField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        qualificationField.setBounds(400, 450, 250, 30);
        backgroundLabel.add(qualificationField);

        addButton = new JButton("Add Nurse");
        addButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        addButton.setBounds(200, 550, 150, 40);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        backgroundLabel.add(addButton);

        nursesInfoButton = new JButton("View Nurses");
        nursesInfoButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        nursesInfoButton.setBounds(460, 550, 150, 40);
        nursesInfoButton.setBackground(Color.ORANGE);
        nursesInfoButton.setForeground(Color.WHITE);
        backgroundLabel.add(nursesInfoButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        backButton.setBounds(350, 620, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(e ->{
        	new AdminDashboard(admin);
        	dispose();
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    DbConn c = new DbConn();
                    String query = "INSERT INTO Nurses(name, age, gender, phone, email, address, qualification) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = c.conn.prepareStatement(query);
                    pstmt.setString(1, nameField.getText());
                    pstmt.setInt(2, Integer.parseInt(ageField.getText()));
                    pstmt.setString(3, genderField.getText());
                    pstmt.setString(4, phoneField.getText());
                    pstmt.setString(5, emailField.getText());
                    pstmt.setString(6, addressField.getText());
                    pstmt.setString(7, qualificationField.getText());
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Nurse added successfully!");
                   nameField.setText("");
                   ageField.setText("");
                   genderField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   addressField.setText("");
                   qualificationField.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Unable to add nurse to the database!");
                }
            }
        });

        nursesInfoButton.addActionListener(e -> {
            new ManageNurses(admin);
            dispose();
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Nurses(new AdminLogin()));
    }
}
