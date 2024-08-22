package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Departments extends JFrame {

    private JTextField departmentNameField, headOfDepartmentField, numberOfBedsField;
    private JButton addButton, viewDepartmentsButton, backButton;

    public Departments(AdminLogin admin) {
        setTitle("Departments");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initialize(admin);
        setVisible(true);
    }

    private void initialize(AdminLogin admin) {
        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Departments");
        titleLabel.setFont(new Font("Bree Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(330, 100, 400, 40);
        backgroundLabel.add(titleLabel);

        // Department name
        JLabel nameLabel = new JLabel("Department Name:");
        nameLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(200, 200, 200, 30);
        backgroundLabel.add(nameLabel);

        departmentNameField = new JTextField(20);
        departmentNameField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        departmentNameField.setBounds(400, 200, 250, 30);
        backgroundLabel.add(departmentNameField);

        // Head of Department
        JLabel headLabel = new JLabel("Head of Department:");
        headLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        headLabel.setForeground(Color.WHITE);
        headLabel.setBounds(200, 250, 200, 30);
        backgroundLabel.add(headLabel);

        headOfDepartmentField = new JTextField(20);
        headOfDepartmentField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        headOfDepartmentField.setBounds(400, 250, 250, 30);
        backgroundLabel.add(headOfDepartmentField);

        // Number of Beds
        JLabel bedsLabel = new JLabel("Number of Beds:");
        bedsLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        bedsLabel.setForeground(Color.WHITE);
        bedsLabel.setBounds(200, 300, 200, 30);
        backgroundLabel.add(bedsLabel);

        numberOfBedsField = new JTextField(20);
        numberOfBedsField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        numberOfBedsField.setBounds(400, 300, 250, 30);
        backgroundLabel.add(numberOfBedsField);

        // Buttons
        addButton = new JButton("Add Department");
        addButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        addButton.setBounds(200, 400, 200, 40);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        backgroundLabel.add(addButton);

        addButton.addActionListener(e -> addDepartment());

        viewDepartmentsButton = new JButton("View Departments");
        viewDepartmentsButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        viewDepartmentsButton.setBounds(460, 400, 200, 40);
        viewDepartmentsButton.setBackground(Color.ORANGE);
        viewDepartmentsButton.setForeground(Color.WHITE);
        viewDepartmentsButton.addActionListener(e -> {
            new ManageDepartments(admin);
            dispose();
        });
        backgroundLabel.add(viewDepartmentsButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        backButton.setBounds(340, 500, 200, 40);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);
        

        backButton.addActionListener(e -> {
        	new AdminDashboard(admin);
        	dispose();
        });
    }

    private void addDepartment() {
        String departmentName = departmentNameField.getText().trim();
        String headOfDepartment = headOfDepartmentField.getText().trim();
        String numberOfBeds = numberOfBedsField.getText().trim();

        if (departmentName.isEmpty() || headOfDepartment.isEmpty() || numberOfBeds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Establish database connection
            DbConn dbConn = new DbConn();
            String query = "INSERT INTO departments (department_name, head_of_department, number_of_beds) VALUES (?, ?, ?)";
            PreparedStatement pstmt = dbConn.conn.prepareStatement(query);
            pstmt.setString(1, departmentName);
            pstmt.setString(2, headOfDepartment);
            pstmt.setInt(3, Integer.parseInt(numberOfBeds));
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Department added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add department.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            departmentNameField.setText("");
            headOfDepartmentField.setText("");
            numberOfBedsField.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding the department.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Departments(new AdminLogin()));
    }

    // Custom JPanel class to hold background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
