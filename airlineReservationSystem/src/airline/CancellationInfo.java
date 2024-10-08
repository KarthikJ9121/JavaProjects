package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class CancellationInfo extends JFrame {
    private JTable table;

    public static void main(String[] args) {
        new CancellationInfo(null);
    }

    public CancellationInfo(GetNewPassengerInfo p) {
        setTitle("Cancellation History");
        getContentPane().setFont(new Font("Bree serif", Font.PLAIN, 13));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        setLocation(200, 50);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel FlightDetails = new JLabel("Cancellation History : ");
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31)); // Updated font
        FlightDetails.setForeground(Color.WHITE);
        FlightDetails.setBounds(300, 70, 570, 35);
        backgroundLabel.add(FlightDetails);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(10, 600, 800, 400);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 120, 800, 400);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Bree serif", Font.BOLD, 14));
        backButton.setBounds(400, 600, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
//                new PassengerDashBoard(new PassengerInfo()).setVisible(true); // Open Passenger MainFrame
            }
        });
        backgroundLabel.add(backButton);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        try {
            DbConn c = new DbConn();
            String str = "select * from cancellation where username ='" + p.getUsername() + "'";

            ResultSet rs = c.stmt.executeQuery(str);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            if (!rs.isBeforeFirst()) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "Thank you for your trust and continued support in our services!\n\nWe're glad you chose to keep your reservation. We look forward to serving you soon.", "Appreciation", JOptionPane.INFORMATION_MESSAGE);

            } else {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
