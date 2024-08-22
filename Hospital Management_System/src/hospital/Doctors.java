package hospital;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class Doctors extends JFrame {

    private JTextField nameField, specializationField;
    private JComboBox<String> genderComboBox;
    private JLabel nameLabel, genderLabel, specializationLabel, Title;
    private JButton addButton, backButton, viewDoctorsButton;
    private Font boldFont;
    private Font comboBoxFont;

    public Doctors(AdminLogin admin) {
        setTitle("Doctors");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initialize(admin);
        setVisible(true);
    }

    private void initialize(AdminLogin admin) {
        boldFont = new Font("Bree Serif", Font.BOLD, 17);
        comboBoxFont = new Font("Bree Serif", Font.BOLD, 14);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
        
        Title = new JLabel("Doctors");
        Title.setFont(new Font("Bree Serif", Font.BOLD, 30));
        Title.setForeground(Color.white);
        Title.setBounds(290, 120, 250, 27);
        backgroundLabel.add(Title);

        nameLabel = new JLabel("Doctor's Name");
        nameLabel.setFont(boldFont);
        nameLabel.setForeground(Color.white);
        nameLabel.setBounds(200, 200, 150, 27);
        backgroundLabel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(350, 200, 200, 27);
        nameField.setFont(boldFont);
        backgroundLabel.add(nameField);

        genderLabel = new JLabel("Gender");
        genderLabel.setFont(boldFont);
        genderLabel.setForeground(Color.white);
        genderLabel.setBounds(200, 250, 150, 27);
        backgroundLabel.add(genderLabel);

        String[] genderOptions = {"Select Gender", "Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genderOptions);
        genderComboBox.setBounds(350, 250, 200, 27);
        genderComboBox.setFont(comboBoxFont);
        backgroundLabel.add(genderComboBox);

        specializationLabel = new JLabel("Specialization");
        specializationLabel.setFont(boldFont);
        specializationLabel.setForeground(Color.white);
        specializationLabel.setBounds(200, 300, 150, 27);
        backgroundLabel.add(specializationLabel);

        specializationField = new JTextField();
        specializationField.setBounds(350, 300, 200, 27);
        specializationField.setFont(boldFont);
        backgroundLabel.add(specializationField);

        addButton = new JButton("Add Doctor");
        addButton.setBounds(200, 350, 150, 30);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        backgroundLabel.add(addButton);
        
        viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.setBounds(400, 350, 150, 30);
        viewDoctorsButton.setBackground(Color.ORANGE);
        viewDoctorsButton.setForeground(Color.WHITE);
        viewDoctorsButton.setFont(boldFont);
        backgroundLabel.add(viewDoctorsButton);

        backButton = new JButton("Back");
        backButton.setBounds(300, 400, 150, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(boldFont);
        backgroundLabel.add(backButton);

        // Add action listeners
        addButton.addActionListener(e -> addDoctor());
        backButton.addActionListener(e -> {
        	new AdminDashboard(admin);
        	dispose();
        });
        viewDoctorsButton.addActionListener(e -> {
            new ManageDoctors(admin);
            dispose();
        });
    }

    private void addDoctor() {
        String doctorName = nameField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String specialization = specializationField.getText();

        if (doctorName.isEmpty() || gender.equals("Select Gender") || specialization.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields!");
        } else {
            try {
                // Insert the doctor details into the database
                DbConn dbConn = new DbConn();
                String query = "INSERT INTO doctors (name, gender, specialization) VALUES (?, ?, ?)";
                PreparedStatement pstmt = dbConn.conn.prepareStatement(query);
                pstmt.setString(1, doctorName);
                pstmt.setString(2, gender);
                pstmt.setString(3, specialization);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Doctor added successfully!");
                // Clear the fields after adding doctor
                nameField.setText("");
                genderComboBox.setSelectedIndex(0);
                specializationField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: Unable to add doctor!");
            }
        }
    }

    // Custom JPanel class to hold background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
            Dimension size = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        new Doctors(null);
    }
}
