package airline;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdminDashBoard extends JFrame {

    public static void main(String[] args) {
        new AdminDashBoard(new AdminLogin()).setVisible(true);
    }

    public AdminDashBoard(AdminLogin admin) {
        super("Admin DashBoard");
        initialize(admin);
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setVisible(true);

        setLayout(null);
        Font buttonFont = new Font("Bree Serif", Font.BOLD, 14);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-30, 0, 1400, 800); // Adjust bounds to cover the entire frame
        add(backgroundLabel);

        JLabel AirlineManagementSystem = new JLabel("Welcome To Indian AirWays, Your home is in the sky..");
        AirlineManagementSystem.setForeground(Color.WHITE);
        AirlineManagementSystem.setFont(new Font("Bree Serif", Font.BOLD, 36));
        AirlineManagementSystem.setHorizontalAlignment(JLabel.CENTER);
        AirlineManagementSystem.setBounds(150, 100, 1000, 55);
        backgroundLabel.add(AirlineManagementSystem);

        JLabel AdminInstruction = new JLabel(admin.getUsername() + ", Please Choose An Action To Do...!!!");
        AdminInstruction.setForeground(Color.WHITE); // Dark blue
        AdminInstruction.setFont(new Font("Bree Serif", Font.BOLD, 36));
        AdminInstruction.setHorizontalAlignment(JLabel.CENTER);
        AdminInstruction.setBounds(150, 150, 1000, 55);
        backgroundLabel.add(AdminInstruction);

        JButton b1 = new JButton("Add New Flight");
        b1.setFont(buttonFont);
        b1.setBounds(430, 250, 200, 40);
        b1.setBackground(new Color(255, 153, 51)); // Orange
        b1.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b1);

        JButton b2 = new JButton("Registered Passengers");
        b2.setFont(buttonFont);
        b2.setBounds(680, 250, 200, 40);
        b2.setBackground(new Color(51, 204, 51)); // Green
        b2.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b2);

        JButton b3 = new JButton("Flight Information");
        b3.setFont(buttonFont);
        b3.setBounds(430, 350, 200, 40);
        b3.setBackground(Color.LIGHT_GRAY); // Red
        b3.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b3);

        JButton b4 = new JButton("Delete Passenger");
        b4.setFont(buttonFont);
        b4.setBounds(680, 350, 200, 40);
        b4.setBackground(new Color(235, 52, 91)); // Blue
        b4.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b4);

        JButton b5 = new JButton("Back");
        b5.setFont(buttonFont);
        b5.setBounds(580, 530, 120, 40);
        b5.setBackground(Color.BLUE); // Light Red
        b5.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b5);

        JButton b6 = new JButton("Admin");
        b6.setFont(buttonFont);
        b6.setBounds(680, 450, 200, 40);
        b6.setBackground(new Color(0, 153, 153)); // Dark Cyan
        b6.setForeground(Color.WHITE); // White text
        backgroundLabel.add(b6);
        
     // Inside the AdminDashBoard class

        JButton deleteFlightButton = new JButton("Delete Flight");
        deleteFlightButton.setBackground(Color.DARK_GRAY);
        deleteFlightButton.setForeground(Color.WHITE);
        deleteFlightButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        deleteFlightButton.setBounds(430, 450, 200, 40);
        backgroundLabel.add(deleteFlightButton);

        deleteFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteFlight().setVisible(true);
            }
        });


        // Button action listeners
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new AddNewFlight(admin);
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new RegisteredPassengers();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new FlightInformation(admin);
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new DeletePassenger();
            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new AdminLogin();
                dispose();
            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Admin();
            }
        });

        // Set frame size
        setSize(1950, 1090);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
