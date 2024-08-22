package hospital;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewPatientDetails extends JFrame {

    public ViewPatientDetails(GetPatientInfo p) {

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);

        setTitle("PATIENT DETAILS");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setLayout(null);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
        
        JLabel patientLabel = new JLabel("Patient Details");
        patientLabel.setForeground(Color.WHITE);
        patientLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        patientLabel.setBounds(150, 24, 442, 35);
        backgroundLabel.add(patientLabel);

        JLabel idLabel = new JLabel("Patient ID: ");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        idLabel.setForeground(Color.WHITE); // Change font color to white
        idLabel.setBounds(60, 90, 150, 27);
        backgroundLabel.add(idLabel);

        JLabel idField = new JLabel(String.valueOf(p.getPatientId()));
        idField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        idField.setForeground(Color.WHITE); // Change font color to white
        idField.setBounds(180, 90, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(idField);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        nameLabel.setForeground(Color.WHITE); // Change font color to white
        nameLabel.setBounds(60, 130, 150, 27);
        backgroundLabel.add(nameLabel);

        JLabel nameField = new JLabel(p.getName());
        nameField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        nameField.setForeground(Color.WHITE); // Change font color to white
        nameField.setBounds(180, 130, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(nameField);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        ageLabel.setForeground(Color.WHITE); // Change font color to white
        ageLabel.setBounds(60, 170, 150, 27);
        backgroundLabel.add(ageLabel);

        JLabel ageField = new JLabel(p.getAge());
        ageField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        ageField.setForeground(Color.WHITE); // Change font color to white
        ageField.setBounds(180, 170, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(ageField);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        genderLabel.setForeground(Color.WHITE); // Change font color to white
        genderLabel.setBounds(60, 220, 150, 27);
        backgroundLabel.add(genderLabel);

        JLabel genderField = new JLabel(p.getGender());
        genderField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        genderField.setForeground(Color.WHITE); // Change font color to white
        genderField.setBounds(180, 220, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(genderField);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        addressLabel.setForeground(Color.WHITE); // Change font color to white
        addressLabel.setBounds(60, 270, 150, 27);
        backgroundLabel.add(addressLabel);

        JLabel addressField = new JLabel(p.getAddress());
        addressField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        addressField.setForeground(Color.WHITE); // Change font color to white
        addressField.setBounds(180, 270, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(addressField);

        JLabel phnoLabel = new JLabel("PH NO: ");
        phnoLabel.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        phnoLabel.setForeground(Color.WHITE); // Change font color to white
        phnoLabel.setBounds(60, 320, 150, 27);
        backgroundLabel.add(phnoLabel);

        JLabel phnoField = new JLabel(p.getPhoneNo());
        phnoField.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        phnoField.setForeground(Color.WHITE); // Change font color to white
        phnoField.setBounds(180, 320, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(phnoField);


        JButton backButton = new JButton("Back"); // Add back button
        backButton.setBounds(60, 480, 100, 30);
        backButton.setBackground(Color.BLUE);  
        backButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Increase font size and bold
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to redirect to patientMainFrame
                 new PatientDashBoard(p); // Redirect to PatientMainFrame
                dispose(); // Close current frame
            }
        });

        JButton editButton = new JButton("Edit Details");
        editButton.setBounds(200, 480, 150, 30);
        editButton.setBackground(Color.ORANGE);
        editButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        editButton.setForeground(Color.WHITE);
        backgroundLabel.add(editButton);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditPatientDetails(p);
                dispose();
            }
        });

        setVisible(true);
        setLocation(200, 100);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new ViewPatientDetails(new GetPatientInfo());
    }
}
