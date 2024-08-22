package airline;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PassengerDashBoard extends JFrame {

    GetNewPassengerInfo p;

    public static void main(String[] args) {
        new PassengerDashBoard(new GetNewPassengerInfo()).setVisible(true);
    }

    public PassengerDashBoard(GetNewPassengerInfo p) {
        super("Passenger Dash Board");
        this.p = p;
        initialize(p);
    }

    private void initialize(GetNewPassengerInfo p) {
        setForeground(Color.CYAN);
        setLayout(null);
        Font f2 = new Font("Bree Serif", Font.BOLD, 15); // Changed font family to Bree Serif and set bold

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel AirlineManagementSystem = new JLabel("Welcome To Hyderabad Airline, Your home in the sky");
        AirlineManagementSystem.setForeground(Color.WHITE);
        AirlineManagementSystem.setFont(new Font("Bree Serif", Font.BOLD, 36)); // Changed font family to Bree Serif and set bold
        AirlineManagementSystem.setHorizontalAlignment(JLabel.CENTER);
        AirlineManagementSystem.setBounds(150, 30, 1000, 55);
        backgroundLabel.add(AirlineManagementSystem);

        String name = p.getName() + ", Please Choose An Action To Do...!!!";

        JLabel AdminInstruction = new JLabel(name);
        AdminInstruction.setForeground(Color.WHITE);
        AdminInstruction.setFont(new Font("Bree Serif", Font.BOLD, 36)); // Changed font family to Bree Serif and set bold
        AdminInstruction.setHorizontalAlignment(JLabel.CENTER);
        AdminInstruction.setBounds(150, 100, 1000, 55);
        backgroundLabel.add(AdminInstruction);

        JButton b1 = new JButton("Passenger Details");
        b1.setFont(f2);
        b1.setBackground(Color.BLUE);
        b1.setForeground(Color.WHITE);

        JButton b2 = new JButton("Flight Routes");
        b2.setFont(f2);
        b2.setBackground(Color.DARK_GRAY);
        b2.setForeground(Color.WHITE);

        JButton b3 = new JButton("Book Ticket");
        b3.setFont(f2);
        b3.setBackground(Color.GREEN);
        b3.setForeground(Color.WHITE);

        JButton b4 = new JButton("Reservation Details");
        b4.setFont(f2);
        b4.setBackground(Color.ORANGE);
        b4.setForeground(Color.WHITE);

        JButton b5 = new JButton("Payment History");
        b5.setFont(f2);
        b5.setBackground(Color.DARK_GRAY);
        b5.setForeground(Color.WHITE);

        JButton b6 = new JButton("Cancel Ticket");
        b6.setFont(f2);
        b6.setBackground(Color.MAGENTA);
        b6.setForeground(Color.WHITE);

        JButton b7 = new JButton("Cancellation History");
        b7.setFont(f2);
        b7.setBackground(Color.PINK);
        b7.setForeground(Color.WHITE);

        JButton b8 = new JButton("Back To Login Page");
        b8.setFont(f2);
        b8.setBackground(Color.LIGHT_GRAY);
        b8.setForeground(Color.WHITE);

        b1.setBounds(430, 250, 200, 40);
        b2.setBounds(680, 250, 200, 40);
        b3.setBounds(430, 350, 200, 40);
        b4.setBounds(680, 350, 200, 40);
        b5.setBounds(430, 450, 200, 40);
        b6.setBounds(680, 450, 200, 40);
        b7.setBounds(430, 550, 200, 40);
        b8.setBounds(680, 550, 200, 40);

        backgroundLabel.add(b1);
        backgroundLabel.add(b2);
        backgroundLabel.add(b3);
        backgroundLabel.add(b4);
        backgroundLabel.add(b5);
        backgroundLabel.add(b6);
        backgroundLabel.add(b7);
        backgroundLabel.add(b8);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new PassengerDetail(p);
//                dispose();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new FlightRoutes(p, "FLIGHT INFORMATION");
//                dispose();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new FlightRoutes(p, "FIND FLIGHT AND BOOK");
//                dispose();
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ReservationInfo(p);
//                dispose();
            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new PaymentHistory(p.getUsername());
//                dispose();
            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new CancelTicket(p);
//                dispose();
            }
        });

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new CancellationInfo(p);
//                dispose();
            }
        });

        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Login();
                dispose();
            }
        });

        setSize(1950, 1090);
        setVisible(true);
    }
}
