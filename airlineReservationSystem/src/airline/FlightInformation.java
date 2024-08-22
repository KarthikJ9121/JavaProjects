package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class FlightInformation extends JFrame {

    private JTable table;

    public static void main(String[] args) {
        new FlightInformation(new AdminLogin()).setVisible(true);
    }

    public FlightInformation(AdminLogin admin) {
        setTitle("Flight Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setLayout(null);
        setVisible(true);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel FlightDetails = new JLabel("Flights");
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31)); // Changed font and made it bold
        FlightDetails.setForeground(Color.WHITE);
        FlightDetails.setBounds(350, 40, 570, 35);
        backgroundLabel.add(FlightDetails);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(40, 100, 800, 450);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 100, 800, 450);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 14)); // Changed font and made it bold
        backButton.setBounds(380, 600, 80, 30);
        backgroundLabel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
//                new AdminDashBoard("").setVisible(true); // Open the main frame
                dispose();
            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        try {
            DbConn c = new DbConn();
            String str = "SELECT * FROM flight";
            ResultSet rs = c.stmt.executeQuery(str);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 650);
        setVisible(true);
        setLocation(200, 50);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
