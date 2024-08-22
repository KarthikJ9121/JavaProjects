package hospital;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class EditPatientDetails extends JFrame {

    JTextField tfName, tfAge, tfGender, tfAddress, tfPhno;

    public EditPatientDetails(GetPatientInfo p) {

        getContentPane().setLayout(null); // Set layout to null for free positioning

        // Add background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        // Set frame properties
        setTitle("EDIT PATIENT DETAILS");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()); // Set frame size based on image
        setResizable(false); // Disable resizing
        
        JLabel lblTitle = new JLabel("Edit Your Details");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial and make it bold
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBounds(170, 200, 700, 50);
        backgroundLabel.add(lblTitle);

        // Add components
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(360, 280, 150, 27);
        backgroundLabel.add(nameLabel);

        tfName = new JTextField(p.getName());
        tfName.setEditable(false); // Set non-editable
        tfName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfName.setBounds(480, 280, 200, 27);
        backgroundLabel.add(tfName);

        JLabel idLabel = new JLabel("Patient ID: ");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        idLabel.setForeground(Color.WHITE);
        idLabel.setBounds(360,320, 150, 27);
        backgroundLabel.add(idLabel);

        JTextField idField = new JTextField(String.valueOf(p.getPatientId()));
        idField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        idField.setEditable(false); // Set non-editable
        idField.setBounds(480, 320, 200, 27);
        backgroundLabel.add(idField);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(360, 360, 150, 27);
        backgroundLabel.add(ageLabel);

        tfAge = new JTextField(p.getAge());
        tfAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfAge.setBounds(480, 360, 200, 27);
        backgroundLabel.add(tfAge);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(360, 400, 150, 27);
        backgroundLabel.add(genderLabel);

        tfGender = new JTextField(p.getGender());
        tfGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfGender.setBounds(480, 400, 200, 27);
        backgroundLabel.add(tfGender);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBounds(360, 440, 150, 27);
        backgroundLabel.add(addressLabel);

        tfAddress = new JTextField(p.getAddress());
        tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfAddress.setBounds(480, 440, 200, 27);
        backgroundLabel.add(tfAddress);

        JLabel phnoLabel = new JLabel("PH NO: ");
        phnoLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        phnoLabel.setForeground(Color.WHITE);
        phnoLabel.setBounds(360, 480, 150, 27);
        backgroundLabel.add(phnoLabel);

        tfPhno = new JTextField(p.getPhoneNo());
        tfPhno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tfPhno.setBounds(480, 480, 200, 27);
        backgroundLabel.add(tfPhno);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(360, 540, 100, 30);
        saveButton.setBackground(Color.GREEN);
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        saveButton.setForeground(Color.WHITE);
        backgroundLabel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDetails(p);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(480, 540, 150, 30);
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
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setVisible(true); // Ensure the frame is visible after setting all properties
    }

    private void updateDetails(GetPatientInfo p) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DbConn().conn;
            String query = "UPDATE Patients SET age=?, gender=?, address=?, phone_no=? WHERE patient_name=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tfAge.getText());
            pstmt.setString(2, tfGender.getText());
            pstmt.setString(3, tfAddress.getText());
            pstmt.setString(4, tfPhno.getText());
            pstmt.setString(5, p.getName());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Details updated successfully!");
            GetPatientInfo patient = GetPatientInfo.getPatientDataFromDB(p.getName());
            new ViewPatientDetails(patient);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating details: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EditPatientDetails(new GetPatientInfo());
    }
}
