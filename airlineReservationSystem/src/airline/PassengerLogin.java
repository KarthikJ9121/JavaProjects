package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PassengerLogin extends JFrame implements ActionListener {
    TextField t1, t2;
    JLabel l1, l2, l3, label;
    JButton b1, b2, b3, b4, backButton; // Added backButton
    Font f1, f2, buttonFont;

    public PassengerLogin() {
        super("Passenger Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0, 1400, 800); // Set bounds to cover the entire frame
        add(backgroundLabel);

        f1 = new Font("Bree Serif", Font.BOLD, 30); // Define font for labels
        f2 = new Font("Bree Serif", Font.BOLD, 20); // Define font for text fields
        buttonFont = new Font("Bree Serif", Font.BOLD, 16); // Define font for buttons

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

        t1 = new TextField(15);
        t1.setFont(f2); // Set font
        t1.setForeground(Color.BLACK); // Set text color to black
        t1.setBounds(400, 200, 200, 30); // Set bounds for text field
        backgroundLabel.add(t1);

        t2 = new TextField(15);
        t2.setEchoChar('*');
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
        b4.setBackground(Color.LIGHT_GRAY); // Set background color to navy blue
        b4.setForeground(Color.WHITE); // Set text color to white
        backgroundLabel.add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        backButton.addActionListener(this); // Register back button action listener

        setVisible(true);
        setLocation(400, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new ForgotPassword();
            dispose();
        }

        if (e.getSource() == b4) {
            new NewPassenger();
            dispose();
        }

        if (e.getSource() == b2) {
            String username = t1.getText();
            String password = t2.getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter the Required Fields!");
            } else {
                try {
                    DbConn conn = new DbConn();

                    String str = "SELECT * FROM PassengerLogin WHERE username = ? AND password = ?";
                    PreparedStatement pstmt = conn.conn.prepareStatement(str);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        GetNewPassengerInfo p = GetNewPassengerInfo.getPassengerDataFromDB(username);
                        new PassengerDashBoard(p);
//                        setVisible(false);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Login");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Action for back button to go to LoginPage
        if (e.getSource() == backButton) {
            new Login(); // Assuming LoginPage is the class name for the login page
//            dispose(); // Close current window
        }
    }

    public static void main(String[] args) {
        new PassengerLogin();
    }
}
