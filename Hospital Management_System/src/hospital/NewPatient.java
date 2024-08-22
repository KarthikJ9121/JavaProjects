package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewPatient extends JFrame {

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    public NewPatient() {
        setTitle("New Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null); // Use null layout for manual positioning

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1360, 770); // Set bounds to cover the entire frame
        add(backgroundLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Make the panel transparent
        contentPanel.setLayout(null); // Use null layout for manual positioning
        contentPanel.setSize(800, 600); // Set bounds to cover the entire frame
        backgroundLabel.add(contentPanel);

        JLabel AddPatient = new JLabel("ENTER YOUR DETAILS");
        AddPatient.setForeground(Color.WHITE);
        AddPatient.setFont(new Font("Tahoma", Font.BOLD, 30));
        AddPatient.setHorizontalAlignment(SwingConstants.CENTER);
        AddPatient.setBounds(125, 30, 550, 50); // Adjusted bounds
        contentPanel.add(AddPatient);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(0, 2, 10, 10));
        formPanel.setBounds(120, 100, 550, 300); // Adjusted bounds for form panel
        contentPanel.add(formPanel);

        JLabel patient_nameLabel = new JLabel("User Name");
        patient_nameLabel.setForeground(Color.WHITE);
        patient_nameLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        patient_nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(patient_nameLabel);

        JTextField patient_nameField = new JTextField();
        patient_nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(patient_nameField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        ageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(ageField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        genderLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(genderLabel);

        JPanel genderPanel = new JPanel();
        genderPanel.setOpaque(false);
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        formPanel.add(genderPanel);

        JLabel phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        phoneNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(phoneNumberField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(addressField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("Bree Serif", Font.BOLD, 17));
        confirmPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(confirmPasswordField);

        JButton saveButton = new JButton("Save");
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.GREEN);
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String patient_name = patient_nameField.getText();
                String ageStr = ageField.getText();
                String phoneNumber = phoneNumberField.getText();
                String address = addressField.getText();

                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                String confirm = new String(confirmPasswordField.getPassword());

                String gender = maleRadioButton.isSelected() ? "male" : "female";

                if (confirm.isEmpty() || patient_name.isEmpty() || ageStr.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                } else if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(null, "Confirm Password does not match the entered password!");
                } else if (!isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Phone Number!");
                } else {
                    int age;
                    try {
                        age = Integer.parseInt(ageStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid age!");
                        return;
                    }

                    try {
                        DbConn conn = new DbConn();

                        String insertPatientQuery = "INSERT INTO patients (patient_name, age, gender, phone_no, address) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = conn.conn.prepareStatement(insertPatientQuery);
                        pstmt.setString(1, patient_name);
                        pstmt.setInt(2, age);
                        pstmt.setString(3, gender);
                        pstmt.setString(4, phoneNumber);
                        pstmt.setString(5, address);
                        pstmt.executeUpdate();
                        pstmt.close();

                        String insertLoginQuery = "INSERT INTO patientslogin (patient_name, password) VALUES (?, ?)";
                        pstmt = conn.conn.prepareStatement(insertLoginQuery);
                        pstmt.setString(1, patient_name);
                        pstmt.setString(2, password);
                        pstmt.executeUpdate();
                        pstmt.close();

                        JOptionPane.showMessageDialog(null, "Welcome to the Hospital Management System. \n You are successfully signed up!");

                        // Fetch the new patient info from the database
                        GetPatientInfo newPatient = GetPatientInfo.getPatientDataFromDB(patient_name);

                        // Clear the input fields
                        patient_nameField.setText("");
                        ageField.setText("");
                        phoneNumberField.setText("");
                        addressField.setText("");
                        passwordField.setText("");
                        confirmPasswordField.setText("");

                        // Open PatientDashboard with the new patient details
                        new PatientDashBoard(newPatient);
                        dispose();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }
                }
            }
        });
        saveButton.setBounds(250, 440, 100, 40); // Adjusted y-position for save button
        contentPanel.add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PatientLogin();
                dispose();
            }
        });
        backButton.setBounds(400, 440, 100, 40); // Adjusted y-position for back button
        contentPanel.add(backButton);

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new NewPatient();
    }
}
