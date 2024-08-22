package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {

    public static void main(String[] args) {
        new AdminDashboard(new AdminLogin()).setVisible(true);
    }

    public AdminDashboard(AdminLogin admin) {
        super("Admin Dashboard");
        initialize(admin);
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setVisible(true);

        setLayout(null);
        Font buttonFont = new Font("Bree Serif", Font.BOLD, 14);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        add(backgroundLabel);

        JLabel hospitalSystemLabel = new JLabel("Hospital Management System");
        hospitalSystemLabel.setForeground(Color.WHITE);
        hospitalSystemLabel.setFont(new Font("Bree Serif", Font.BOLD, 36));
        hospitalSystemLabel.setHorizontalAlignment(JLabel.CENTER);
        hospitalSystemLabel.setBounds(150, 100, 1000, 55);
        backgroundLabel.add(hospitalSystemLabel);

        JLabel adminInstructionLabel = new JLabel(admin.getUsername() + ", Please Choose An Action To Do...!!!");
        adminInstructionLabel.setForeground(Color.WHITE);
        adminInstructionLabel.setFont(new Font("Bree Serif", Font.BOLD, 36));
        adminInstructionLabel.setHorizontalAlignment(JLabel.CENTER);
        adminInstructionLabel.setBounds(150, 150, 1000, 55);
        backgroundLabel.add(adminInstructionLabel);

        JButton addDoctorButton = new JButton("Doctors");
        addDoctorButton.setFont(buttonFont);
        addDoctorButton.setBounds(180, 250, 200, 40);
        addDoctorButton.setBackground(new Color(255, 153, 51)); // Orange
        addDoctorButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(addDoctorButton);

        JButton addNurseButton = new JButton("Nurses");
        addNurseButton.setFont(buttonFont);
        addNurseButton.setBounds(430, 250, 200, 40);
        addNurseButton.setBackground(new Color(102, 102, 255)); // Blue
        addNurseButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(addNurseButton);

        JButton addDepartmentButton = new JButton("Departments");
        addDepartmentButton.setFont(buttonFont);
        addDepartmentButton.setBounds(680, 250, 200, 40);
        addDepartmentButton.setBackground(new Color(0, 204, 204)); // Cyan
        addDepartmentButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(addDepartmentButton);

        JButton managePatientsButton = new JButton("Manage Patients");
        managePatientsButton.setFont(buttonFont);
        managePatientsButton.setBounds(180, 350, 200, 40);
        managePatientsButton.setBackground(new Color(255, 153, 204)); // Pink
        managePatientsButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(managePatientsButton);

        JButton manageAppointmentsButton = new JButton("Manage Appointments");
        manageAppointmentsButton.setFont(buttonFont);
        manageAppointmentsButton.setBounds(430, 350, 200, 40);
        manageAppointmentsButton.setBackground(new Color(0, 153, 153)); // Dark Cyan
        manageAppointmentsButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(manageAppointmentsButton);

        JButton manageReportsButton = new JButton("Manage Reports");
        manageReportsButton.setFont(buttonFont);
        manageReportsButton.setBounds(680, 350, 200, 40);
        manageReportsButton.setBackground(new Color(255, 204, 102)); // Light Orange
        manageReportsButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(manageReportsButton);

        JButton DeleteHistory = new JButton("Delete History");
        DeleteHistory.setFont(buttonFont);
        DeleteHistory.setBounds(180, 450, 200, 40);
        DeleteHistory.setBackground(Color.ORANGE); // Blue
        DeleteHistory.setForeground(Color.WHITE); // White text
        backgroundLabel.add(DeleteHistory);
        
        JButton Inventory = new JButton("Inventory");
        Inventory.setFont(buttonFont);
        Inventory.setBounds(430, 450, 200, 40);
        Inventory.setBackground(new Color(0, 102, 204)); // Blue
        Inventory.setForeground(Color.WHITE); // White text
        backgroundLabel.add(Inventory);
        
        // New Admin button
        JButton adminButton = new JButton("Admin");
        adminButton.setFont(buttonFont);
        adminButton.setBounds(680, 450, 200, 40);
        adminButton.setBackground(new Color(255, 153, 204)); // Blue
        adminButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(adminButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBounds(430, 550, 200, 40);
        logoutButton.setBackground(new Color(255, 51, 51)); // Red
        logoutButton.setForeground(Color.WHITE); // White text
        backgroundLabel.add(logoutButton);

        // Button action listeners
        addDoctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Doctors(admin);
                dispose();
            }
        });

        managePatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ManagePatients(admin);
                dispose();
            }
        });

        manageAppointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ManageAppointments(admin);
                dispose();
            }
        });

        manageReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ManageReports(admin);
                dispose();
            }
        });
        
        DeleteHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new DeleteHistory(admin);
            }
        });
        
        Inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ManageInventory(admin);
            }
        });
        
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Admin(admin);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose(); // Close the current frame
            }
        });

        addNurseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Nurses(admin);
                dispose();
            }
        });

        addDepartmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Departments(admin);
                dispose();
            }
        });

        // Set frame size
        setSize(1950, 1090);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
