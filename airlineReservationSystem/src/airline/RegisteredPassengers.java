package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegisteredPassengers extends JFrame {

    private JTable table;

    public static void main(String[] args) {
        new RegisteredPassengers().setVisible(true);
    }

    public RegisteredPassengers() {
    	
    	setTitle("Registered Passengers");
        // Set layout to null
        getContentPane().setLayout(null);
        getContentPane().setFont(new Font("Bree Serif", Font.BOLD, 13)); // Changed font family to Bree Serif

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setVisible(true);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel FlightDetails = new JLabel("Passengers");
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31)); // Changed font family to Bree Serif
        FlightDetails.setForeground(Color.WHITE); // Thick Orange
        FlightDetails.setBounds(350, 40, 570, 35);
        backgroundLabel.add(FlightDetails);
        
     // Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 14)); // Changed font family to Bree Serif and set bold
        backButton.setBounds(350, 600, 80, 30);
        backgroundLabel.add(backButton);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(40, 100, 800, 450);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 100, 800, 450);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                // Replace "MainFrame" with the name of your main frame class
//                new AdminDashBoard().setVisible(true); // Open the main frame
            }
        });

        try {
            DbConn c = new DbConn();
            String str = "select * from passenger";

            ResultSet rs = c.stmt.executeQuery(str);
            // Convert ResultSet to a TableModel using DbUtils
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch data from database!");
        }

       
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 650);
        setVisible(true);
        setLocation(200, 50);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
}
