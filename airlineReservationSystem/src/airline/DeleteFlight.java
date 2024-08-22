package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteFlight extends JFrame {

    private JTable table;
    private JTextField tfFlightCode;

    public DeleteFlight() {
        setTitle("Delete Flight");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 650);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel titleLabel = new JLabel("Delete Flight");
        titleLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(400, 30, 200, 30);
        backgroundLabel.add(titleLabel);

        // Flight table
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(40, 100, 800, 450);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 100, 800, 450);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        // Retrieve flight information and populate the table
        try {
            DbConn c = new DbConn();
            String str = "SELECT * FROM flight";
            ResultSet rs = c.stmt.executeQuery(str);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightCodeLabel.setForeground(Color.WHITE);
        flightCodeLabel.setBounds(60, 580, 100, 20);
        backgroundLabel.add(flightCodeLabel);

        tfFlightCode = new JTextField();
        tfFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfFlightCode.setBounds(160, 580, 150, 25);
        backgroundLabel.add(tfFlightCode);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteButton.setBounds(350, 580, 120, 30);
        backgroundLabel.add(deleteButton);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String flightCode = tfFlightCode.getText().trim();
                if (!flightCode.isEmpty()) {
                    deleteFlight(flightCode);
                    tfFlightCode.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the flight code!");
                }
            }
        });
        
        JButton back = new JButton("Back");
        back.setBounds(280, 650, 100, 27);
        back.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        backgroundLabel.add(back);


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to passengerLogin
                dispose();
            }
        });
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void deleteFlight(String flightCode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlineReservation", "root", "J.9010048072");
            String query = "DELETE FROM flight WHERE flight_code=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, flightCode);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Flight deleted successfully!");
                refreshTable(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(null, "Flight not found or could not be deleted!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting flight: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void refreshTable() {
        try {
            DbConn c = new DbConn();
            String str = "SELECT * FROM flight";
            ResultSet rs = c.stmt.executeQuery(str);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
