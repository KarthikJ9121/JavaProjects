package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class ReservationInfo extends JFrame {
    private JTable table;

    public ReservationInfo(GetNewPassengerInfo p) {
    	setTitle("Reservation Details");
    	
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocation(200, 50);

        // Background Image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0,backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel FlightDetails = new JLabel("Your Flight Bookings : ");
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31));
        FlightDetails.setForeground(Color.white);
        FlightDetails.setBounds(330, 70, 570, 35);
        backgroundLabel.add(FlightDetails);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(40, 120, 800, 400);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 120, 800, 400);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(420, 600, 80, 30);
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new PassengerDashBoard(p);
            dispose();
        });
        backgroundLabel.add(backButton);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        try {
            DbConn c = new DbConn();
            String str = "select * from reservation where username ='" + p.getUsername() + "'";

            ResultSet rs = c.stmt.executeQuery(str);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            if (!rs.isBeforeFirst()) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "Welcome aboard!\n\n\tWe're excited to take you on an amazing journey.\n\tDon't miss out on our incredible flight experiences. Book your flight now and create unforgettable memories!");

            } else {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public static void main(String[] args) {
        new ReservationInfo(new GetNewPassengerInfo ());
    }
}
