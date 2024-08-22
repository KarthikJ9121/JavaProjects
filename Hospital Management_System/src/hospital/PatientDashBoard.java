package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientDashBoard extends JFrame {

    GetPatientInfo p;

    public static void main(String[] args) {
        new PatientDashBoard(new GetPatientInfo()).setVisible(true);
    }

    public PatientDashBoard(GetPatientInfo p) {
        super("Patient DashBoard"); // Setting the title
        this.p = p;
        initialize(p);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void initialize(GetPatientInfo p) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel titleLabel = new JLabel("Welcome to Hospital Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Bree Serif", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(60, 70, 1000, 55);
        backgroundLabel.add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome " + p.getName() + " ! Please do an action.");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(130, 150, 800, 50);
        backgroundLabel.add(welcomeLabel);

        Font buttonFont = new Font("Bree Serif", Font.BOLD, 15);

        JButton appointmentsButton = new JButton("Appointments");
        appointmentsButton.setFont(buttonFont);
        appointmentsButton.setBackground(new Color(51, 153, 255)); // Blue background
        appointmentsButton.setForeground(Color.WHITE);
        appointmentsButton.setBounds(320, 250,  200, 40); // Adjusted bounds
        backgroundLabel.add(appointmentsButton);

        JButton viewReportsButton = new JButton("Delete History");
        viewReportsButton.setFont(buttonFont);
        viewReportsButton.setBackground(new Color(255, 102, 102)); // Pink background
        viewReportsButton.setForeground(Color.WHITE);
        viewReportsButton.setBounds(540, 250,  200, 40); // Adjusted bounds
        backgroundLabel.add(viewReportsButton);

        JButton manageProfileButton = new JButton("Manage Profile");
        manageProfileButton.setFont(buttonFont);
        manageProfileButton.setBackground(new Color(102, 204, 102)); // Green background
        manageProfileButton.setForeground(Color.WHITE);
        manageProfileButton.setBounds(420, 330,  200, 40); // Adjusted bounds
        backgroundLabel.add(manageProfileButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBackground(Color.RED); // Red background
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBounds(440, 410,  160, 40); // Adjusted bounds
        backgroundLabel.add(logoutButton);

        appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Appointments(p);
                dispose();
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new CancellationHistory(p);
                dispose();
            }
        });

        manageProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ViewPatientDetails(p);
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new PatientLogin();
                dispose();
            }
        });

        setSize(1200, 400);
        setVisible(true);
    }
}
