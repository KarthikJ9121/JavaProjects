package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    TextField t1, t2;
    JLabel l1, l2, label;
    JButton b1, b2, b3; // Removed backButton
    Font f1, f2, buttonFont;

    public AdminLogin() {
        super("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null); // Removed grid layout

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0, 1400, 800); // Set bounds to cover the entire frame
        add(backgroundLabel);

        f1 = new Font("Arial", Font.BOLD, 25); // Define font for labels
        f2 = new Font("Arial", Font.PLAIN, 20); // Define font for text fields
        buttonFont = new Font("Arial", Font.BOLD, 20); // Define font for buttons
        
        JLabel lblWelcome = new JLabel("Welcome to Hospital Management System !!");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial and make it bold
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        lblWelcome.setBounds(100, 100, 700, 50);
        backgroundLabel.add(lblWelcome);

        l1 = new JLabel("Username");
        l1.setBounds(200, 200, 200, 30); // Set bounds for label
        l1.setForeground(Color.WHITE); // Set text color to white
        l1.setFont(f1); // Set font
        backgroundLabel.add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(200, 250, 200, 30); // Set bounds for label
        l2.setForeground(Color.WHITE); // Set text color to white
        l2.setFont(f1); // Set font
        backgroundLabel.add(l2);

        t1 = new TextField(15);
        t1.setBounds(400, 200, 200, 30); // Set bounds for text field
        t1.setFont(f2); // Set font
        t1.setForeground(Color.BLACK); // Set text color to black
        backgroundLabel.add(t1);

        t2 = new TextField(15);
        t2.setEchoChar('*');
        t2.setBounds(400, 250, 200, 30); // Set bounds for text field
        t2.setFont(f2); // Set font
        t2.setForeground(Color.BLACK); // Set text color to black
        backgroundLabel.add(t2);

        b1 = new JButton("Clear");
        b1.setBounds(200, 350, 100, 40); // Set bounds for button
        b1.setBackground(Color.ORANGE); // Set background color to orange
        b1.setForeground(Color.WHITE); // Set text color to white
        b1.setFont(buttonFont); // Set font
        backgroundLabel.add(b1);

        b2 = new JButton("Sign In");
        b2.setBounds(350, 350, 100, 40); // Set bounds for button
        b2.setBackground(Color.GREEN); // Set background color to light green
        b2.setForeground(Color.WHITE); // Set text color to white
        b2.setFont(buttonFont); // Set font
        backgroundLabel.add(b2);

        b3 = new JButton("Back");
        b3.setBounds(500, 350, 100, 40); // Set bounds for button
        b3.setBackground(Color.BLUE); // Set background color to blue
        b3.setForeground(Color.WHITE); // Set text color to white
        b3.setFont(buttonFont); // Set font
        backgroundLabel.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setVisible(true);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            t1.setText("");
            t2.setText("");
        }

        if (e.getSource() == b3) {
            new Login();
            dispose(); // Dispose the current AdminLogin frame
        }

        if (e.getSource() == b2) {
            try {
                DbConn conn = new DbConn();

                String s1 = t1.getText();
                String s2 = t2.getText();

                if (s1.isEmpty() || s2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password!");
                } else {
                    String query = "SELECT * FROM adminlogin WHERE username = ? AND password = ?";
                    PreparedStatement pst = conn.conn.prepareStatement(query);
                    pst.setString(1, s1);
                    pst.setString(2, s2);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        dispose(); // Close the current AdminLogin frame
                        AdminLogin admin = new AdminLogin();
                        admin.setUsername(rs.getString("username")); // Set the username
                        new AdminDashboard(admin); // Open the AdminDashboard
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    }

                    pst.close();
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            t1.setText("");
            t2.setText("");
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
