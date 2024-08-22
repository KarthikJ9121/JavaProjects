package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class PaymentHistory extends JFrame {
    private JTable table;

    public static void main(String[] args) {
        new PaymentHistory(null);
    }

    public PaymentHistory(String username) {
    	
    	setTitle("Payment History");

        getContentPane().setBackground(Color.WHITE);
        getContentPane().setFont(new Font("Bree Serif", Font.PLAIN, 13)); // Changed font family to Bree Serif

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocation(200, 50);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel flightDetails = new JLabel("Your Payment Details: ");
        flightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31)); // Changed font family to Bree Serif and set bold
        flightDetails.setForeground(Color.WHITE);
        flightDetails.setBounds(250, 50, 570, 35);
        backgroundLabel.add(flightDetails);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(40, 120, 800, 400);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 120, 800, 400);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 600, 100, 30);
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 16)); // Set font for button
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLUE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                new PassengerDashBoard(new PassengerInfo()).setVisible(true); // Redirect to PassengerMainFrame
                dispose(); // Close current frame
            }
        });

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            DbConn c = new DbConn();
            String str = "select * from payment where username ='" + username + "'";
            ResultSet rs = c.stmt.executeQuery(str);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            if (!rs.isBeforeFirst()) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "It looks like you haven't made any payments yet!");

            } else {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
