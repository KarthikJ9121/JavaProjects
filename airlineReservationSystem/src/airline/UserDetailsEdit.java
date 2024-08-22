package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UserDetailsEdit extends JFrame {

    JTextField tfName, tfGmail, tfGender, tfNationality, tfPassport, tfPhno, tfAddress;

    public UserDetailsEdit(GetNewPassengerInfo p) {

        getContentPane().setLayout(null); // Set layout to null for free positioning

        // Add background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        // Set frame properties
        setTitle("EDIT PASSENGER DETAILS");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()); // Set frame size based on image
        setResizable(false); // Disable resizing

        // Add components
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        usernameLabel.setForeground(Color.white);
        usernameLabel.setBounds(60, 80, 150, 27);
        backgroundLabel.add(usernameLabel);

        JLabel tfUsername = new JLabel(p.getUsername());
        tfUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
        tfUsername.setForeground(Color.WHITE);
        tfUsername.setBounds(180, 80, 200, 27);
        backgroundLabel.add(tfUsername);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(60, 120, 150, 27);
        backgroundLabel.add(nameLabel);

        tfName = new JTextField(p.getName());
        tfName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfName.setBounds(180, 120, 200, 27);
        backgroundLabel.add(tfName);

        JLabel gmailLabel = new JLabel("Gmail: ");
        gmailLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        gmailLabel.setForeground(Color.WHITE);
        gmailLabel.setBounds(60, 170, 150, 27);
        backgroundLabel.add(gmailLabel);

        tfGmail = new JTextField(p.getGmail());
        tfGmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfGmail.setBounds(180, 170, 200, 27);
        backgroundLabel.add(tfGmail);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(60, 220, 150, 27);
        backgroundLabel.add(genderLabel);

        tfGender = new JTextField(p.getGender());
        tfGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfGender.setBounds(180, 220, 200, 27);
        backgroundLabel.add(tfGender);

        JLabel nationalityLabel = new JLabel("Nationality: ");
        nationalityLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        nationalityLabel.setForeground(Color.WHITE);
        nationalityLabel.setBounds(60, 270, 150, 27);
        backgroundLabel.add(nationalityLabel);

        tfNationality = new JTextField(p.getNationality());
        tfNationality.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfNationality.setBounds(180, 270, 200, 27);
        backgroundLabel.add(tfNationality);

        JLabel passportLabel = new JLabel("Passport: ");
        passportLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        passportLabel.setForeground(Color.WHITE);
        passportLabel.setBounds(60, 320, 150, 27);
        backgroundLabel.add(passportLabel);

        tfPassport = new JTextField(p.getPassport_no());
        tfPassport.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfPassport.setBounds(180, 320, 200, 27);
        backgroundLabel.add(tfPassport);

        JLabel phnoLabel = new JLabel("PH NO: ");
        phnoLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        phnoLabel.setForeground(Color.WHITE);
        phnoLabel.setBounds(60, 370, 150, 27);
        backgroundLabel.add(phnoLabel);

        tfPhno = new JTextField(p.getPhonenumber());
        tfPhno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfPhno.setBounds(180, 370, 200, 27);
        backgroundLabel.add(tfPhno);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBounds(60, 420, 150, 27);
        backgroundLabel.add(addressLabel);

        tfAddress = new JTextField(p.getAddress());
        tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfAddress.setBounds(180, 420, 200, 27);
        backgroundLabel.add(tfAddress);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(60, 480, 100, 30);
        saveButton.setBackground(Color.GREEN);
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        saveButton.setForeground(Color.WHITE);
        backgroundLabel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDetails(p);
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 480, 150, 30);
        cancelButton.setBackground(Color.RED);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        cancelButton.setForeground(Color.WHITE);
        backgroundLabel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Ensure the frame is visible after setting all properties
    }

    private void updateDetails(GetNewPassengerInfo p) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlineReservation", "root", "J.9010048072");
            String query = "UPDATE passenger SET name=?, gmail=?, gender=?, nationality=?, passport=?, phonenumber=?, address=? WHERE username=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tfName.getText());
            pstmt.setString(2, tfGmail.getText());
            pstmt.setString(3, tfGender.getText());
            pstmt.setString(4, tfNationality.getText());
            pstmt.setString(5, tfPassport.getText());
            pstmt.setString(6, tfPhno.getText());
            pstmt.setString(7, tfAddress.getText());
            pstmt.setString(8, p.getUsername());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Details updated successfully!");
            GetNewPassengerInfo passenger = GetNewPassengerInfo.getPassengerDataFromDB(p.getUsername());
            new PassengerDashBoard(passenger);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating details: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UserDetailsEdit(new GetNewPassengerInfo());
    }
}
