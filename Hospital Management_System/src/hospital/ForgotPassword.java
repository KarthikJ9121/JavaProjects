package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForgotPassword extends JFrame implements ActionListener {

    TextField t1, t2, t3;
    JLabel l1, l2, l3, label;
    JButton b2, b3, backButton; // Added backButton
    Font f1, f2, buttonFont;

    public ForgotPassword() {
        super("Forgot Password");

        setBackground(Color.WHITE);

        f1 = new Font("Bree Serif", Font.BOLD, 20); // Define font for labels
        f2 = new Font("Bree Serif", Font.BOLD, 15); // Define font for text fields
        buttonFont = new Font("Bree Serif", Font.BOLD, 18); // Define font for buttons
        setLayout(null); // Use null layout

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0, 1400, 800); // Set bounds to cover the entire frame
        add(backgroundLabel);
        
        JLabel title = new JLabel("Please reset your Password");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial and make it bold
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(50, 110, 700, 30);
        backgroundLabel.add(title);

        l1 = new JLabel("Username");
        l1.setFont(f1); // Set font
        l1.setForeground(Color.WHITE); // Set text color to white
        l1.setBounds(200, 200, 150, 30); // Set bounds
        backgroundLabel.add(l1);

        l2 = new JLabel("New Password");
        l2.setFont(f1); // Set font
        l2.setForeground(Color.WHITE); // Set text color to white
        l2.setBounds(200, 250, 150, 30); // Set bounds
        backgroundLabel.add(l2);

        l3 = new JLabel("Confirm Password");
        l3.setFont(f1); // Set font
        l3.setForeground(Color.WHITE); // Set text color to white
        l3.setBounds(200, 300, 200, 30); // Set bounds
        backgroundLabel.add(l3);

        t1 = new TextField(15);
        t1.setBounds(400, 200, 200, 30); // Set bounds
        t1.setFont(f2);
        backgroundLabel.add(t1);

        t2 = new TextField(15);
        t2.setEchoChar('*');
        t2.setBounds(400, 250, 200, 30); // Set bounds
        t2.setFont(f2);
        backgroundLabel.add(t2);

        t3 = new TextField(15);
        t3.setEchoChar('*');
        t3.setBounds(400, 300, 200, 30); // Set bounds
        backgroundLabel.add(t3);

        b2 = new JButton("Save");
        b2.setFont(buttonFont); // Set font
        b2.setBackground(Color.GREEN); // Set background color to green
        b2.setForeground(Color.WHITE); // Set text color to white
        b2.setBounds(200, 400, 150, 40); // Set bounds
        backgroundLabel.add(b2);

        backButton = new JButton("Back"); // New back button
        backButton.setFont(buttonFont); // Set font
        backButton.setBackground(Color.BLUE); // Set background color to navy blue
        backButton.setForeground(Color.WHITE); // Set text color to white
        backButton.setBounds(400, 400, 150, 40); // Set bounds
        backgroundLabel.add(backButton);

        b2.addActionListener(this);
        backButton.addActionListener(this); // Register back button action listener

        setVisible(true);
        setSize(800, 600);
        setLocation(400, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2) {
            try {
                try {
                    DbConn c = new DbConn();
                    String str1 = "SELECT COUNT(*) AS count FROM patientslogin WHERE patient_name = ?";
                    PreparedStatement pstmt = c.conn.prepareStatement(str1);
                    pstmt.setString(1, t1.getText());
                    ResultSet rs = pstmt.executeQuery();

                    rs.next();
                    int count = rs.getInt("count");

                    if (count > 0) {
                        String username = t1.getText();
                        String password = t2.getText();
                        String password1 = t3.getText();

                        if (!password.equals(password1) || password.isEmpty() || password1.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Confirm Password is Matching with the password you Entered!");
                        } else {
                            try {

                                DbConn conn = new DbConn();

                                String updateQuery = "UPDATE patientslogin SET password = ? WHERE patient_name = ?";
                                PreparedStatement pstmt1 = conn.conn.prepareStatement(updateQuery);
                                pstmt1.setString(1, password);
                                pstmt1.setString(2, username);

                                int rowsUpdated = pstmt1.executeUpdate(); // Use executeUpdate for update queries

                                if (rowsUpdated > 0) {
                                    JOptionPane.showMessageDialog(null, "Password Updated Successfully");
                                    setVisible(false);
                                    new PatientLogin();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Login");
                                }

                            } catch (Exception e1) {
                                System.out.println(e1);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter your username.");
                    }

                } catch (Exception ee) {
                    ee.printStackTrace();
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == backButton) {
            new PatientLogin(); // Assuming PatientLogin is the class name for the patient login page
            dispose(); // Close current window
        }
    }

    public static void main(String[] args) {
        new ForgotPassword();
    }
}
