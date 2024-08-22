package airline;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
    JButton b1, b2, b3, backButton; // Added backButton
    Font f1, f2, buttonFont;

    public AdminLogin() {
        super("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null); // Removed grid layout

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0, 1400, 800); // Set bounds to cover the entire frame
        add(backgroundLabel);

        f1 = new Font("Bree Serif", Font.BOLD, 25); // Define font for labels
        f2 = new Font("Bree Serif", Font.BOLD, 20); // Define font for text fields
        buttonFont = new Font("Bree Serif", Font.BOLD, 20); // Define font for buttons

        l1 = new JLabel("Username");
        l1.setBounds(200, 200, 200, 30); // Set bounds for label
        l1.setForeground(Color.white); // Set text color to white
        l1.setFont(f1); // Set font
        backgroundLabel.add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(200, 250, 200, 30); // Set bounds for label
        l2.setForeground(Color.white); // Set text color to white
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

        b1 = new JButton("Reset");
        b1.setBounds(200, 350, 100, 30); // Set bounds for button
        b1.setBackground(Color.ORANGE); // Set background color to orange
        b1.setForeground(Color.WHITE); // Set text color to white
        b1.setFont(buttonFont); // Set font
        backgroundLabel.add(b1);

        b2 = new JButton("SignIn");
        b2.setBounds(350, 350, 100, 30); // Set bounds for button
        b2.setBackground(Color.GREEN); // Set background color to light green
        b2.setForeground(Color.WHITE); // Set text color to white
        b2.setFont(buttonFont); // Set font
        backgroundLabel.add(b2);

        b3 = new JButton("Back");
        b3.setBounds(500, 350, 100, 30); // Set bounds for button
        b3.setBackground(Color.BLUE); // Set background color to red
        b3.setForeground(Color.WHITE); // Set text color to white
        b3.setFont(buttonFont); // Set font
        backgroundLabel.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
//        backButton.addActionListener(this); // Register back button action listener

        setVisible(true);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
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
                    JOptionPane.showMessageDialog(null, "Please enter Required Fields!");
                } else {
                    String str = "select * from admin where username = '" + s1 + "' and password = '" + s2 + "'";
                    ResultSet rs = conn.stmt.executeQuery(str);
                    if (rs.next()) {
                        // Dispose the current AdminLogin frame
                    	dispose();
                        
                        // Create a new AdminLogin object and set the username
                        AdminLogin admin = new AdminLogin();
                        admin.setUsername(rs.getString("username"));
                        dispose();
                        // Open the AdminDashboard with the AdminLogin reference
                        new AdminDashBoard(admin);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                    }
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
