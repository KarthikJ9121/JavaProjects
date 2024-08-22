package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientLogin extends JFrame implements ActionListener {
    private JTextField t1;
    private JPasswordField t2;
    private JLabel l1, l2, l3;
    private JButton b1, b2, b3, b4, backButton;
    private Font f1, f2, buttonFont;

    public PatientLogin() {
        super("Patient Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0, 1400, 800); // Set bounds to cover the entire frame
        add(backgroundLabel);

        f1 = new Font("Bree Serif", Font.BOLD, 30); // Define font for labels
        f2 = new Font("Bree Serif", Font.BOLD, 20); // Define font for text fields
        buttonFont = new Font("Bree Serif", Font.BOLD, 16); // Define font for buttons
        
        JLabel lblWelcome = new JLabel("Welcome to Hospital Management System !!");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial and make it bold
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        lblWelcome.setBounds(100, 100, 700, 50);
        backgroundLabel.add(lblWelcome);

        l1 = new JLabel("Username");
        l1.setFont(f1); // Set font
        l1.setForeground(Color.white); // Set text color to white
        l1.setBounds(200, 200, 200, 30); // Set bounds for label
        backgroundLabel.add(l1);

        l2 = new JLabel("Password");
        l2.setFont(f1); // Set font
        l2.setForeground(Color.white); // Set text color to white
        l2.setBounds(200, 250, 200, 30); // Set bounds for label
        backgroundLabel.add(l2);

        t1 = new JTextField();
        t1.setFont(f2); // Set font
        t1.setForeground(Color.BLACK); // Set text color to black
        t1.setBounds(400, 200, 200, 30); // Set bounds for text field
        backgroundLabel.add(t1);

        t2 = new JPasswordField();
        t2.setFont(f2); // Set font
        t2.setForeground(Color.BLACK); // Set text color to black
        t2.setBounds(400, 250, 200, 30); // Set bounds for text field
        backgroundLabel.add(t2);

        b1 = new JButton("Forgot?");
        b1.setFont(buttonFont); // Set font
        b1.setBounds(200, 350, 100, 30); // Set bounds for button
        b1.setBackground(Color.ORANGE); // Set background color to cyan
        b1.setForeground(Color.WHITE); // Set text color to white
        backgroundLabel.add(b1);

        b2 = new JButton("SignIn");
        b2.setFont(buttonFont); // Set font
        b2.setBounds(350, 350, 100, 30); // Set bounds for button
        b2.setBackground(Color.GREEN); // Set background color to cyan
        b2.setForeground(Color.WHITE); // Set text color to white
        backgroundLabel.add(b2);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont); // Set font
        backButton.setBounds(500, 350, 100, 30); // Set bounds for button
        backButton.setBackground(Color.BLUE); // Set background color to light gray
        backButton.setForeground(Color.WHITE); // Set text color to black
        backgroundLabel.add(backButton);
        
        l3 = new JLabel("New User ?");
        l3.setFont(new Font("Bree Serif", Font.BOLD, 22)); // Set font
        l3.setForeground(Color.white); // Set text color to white
        l3.setBounds(200, 420, 200, 30); // Set bounds for label
        backgroundLabel.add(l3);

        b4 = new JButton("Sign Up"); // New back button
        b4.setFont(buttonFont); // Set font
        b4.setBounds(350, 420, 100, 30); // Set bounds for back button
        b4.setBackground(Color.magenta); // Set background color to navy blue
        b4.setForeground(Color.WHITE); // Set text color to white
        backgroundLabel.add(b4);

        // Add action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        backButton.addActionListener(this); // Register back button action listener

        setVisible(true);
        setLocation(400, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new ForgotPassword();
            dispose();
        }

        if (e.getSource() == b4) {
            new NewPatient();
            dispose();
        }

        if (e.getSource() == b2) {
            String username = t1.getText().trim(); // Trim to remove leading/trailing spaces
            String password = String.valueOf(t2.getPassword()); // Get password as String

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password are required fields.");
            } else {
                try {
                    DbConn conn = new DbConn();

                    // Prepare SQL statement with placeholders to prevent SQL injection
                    String query = "SELECT * FROM PatientsLogin WHERE patient_name = ? AND password = ?";
                    PreparedStatement pstmt = conn.conn.prepareStatement(query);
                    pstmt.setString(1, username);
                    
                    
                    pstmt.setString(2, password);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Successful login
                        GetPatientInfo patientInfo = GetPatientInfo.getPatientDataFromDB(username);
                        new PatientDashBoard(patientInfo);
                        dispose();
                    } else {
                        // Invalid login
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password.");
                        t1.setText("");
                        t2.setText("");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error occurred.");
                }
            }
        }

        if (e.getSource() == backButton) {
            new Login(); // Navigate back to the main login screen
            dispose(); // Dispose current window
        }
    }

    public static void main(String[] args) {
        new PatientLogin();
    }
}
