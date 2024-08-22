package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class FlightRoutes extends JFrame { // Second Frame

    private JTable table;
    private TextField textField;
    JLabel Book, Class, ReservationDetails, Pnrno, Ticketid, Fcode, Jnydate, Jnytime, Source, Destination, label, label1;
    JButton Show, pay, backButton; // Added backButton
    Font f2;
    String src, dst, cls;

    public static void main(String[] args) {
        new FlightRoutes(null, "").setVisible(true);
    }

    public FlightRoutes(GetNewPassengerInfo p, String title) {
    	setTitle("Flight Routes");
    	
        f2 = new Font("Bree Serif", Font.BOLD, 15); // Changed font family and made it bold

        getContentPane().setBackground(Color.WHITE);
        getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setLayout(null);
        setVisible(true);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel FlightDetails = new JLabel(title);
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31)); // Changed font family and made it bold
        FlightDetails.setForeground(Color.WHITE);
        FlightDetails.setBounds(150, 20, 570, 35);
        backgroundLabel.add(FlightDetails);

        JButton btnShow = new JButton("SHOW");
        btnShow.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Changed font family and made it bold
        btnShow.setForeground(Color.WHITE);
        btnShow.setBackground(Color.GREEN);

        Source = new JLabel("SOURCE");
        Source.setFont(f2);
        Source.setBounds(30, 100, 70, 30);
        Source.setForeground(Color.WHITE);
        backgroundLabel.add(Source);

        Destination = new JLabel("DESTINATION");
        Destination.setFont(f2);
        Destination.setBounds(280, 100, 110, 30);
        Destination.setForeground(Color.WHITE);
        backgroundLabel.add(Destination);

        Class = new JLabel("CLASS");
        Class.setFont(f2);
        Class.setBounds(580, 100, 50, 30);
        Class.setForeground(Color.WHITE);
        backgroundLabel.add(Class);

        String[] items1 = { "Hyderabad", "Delhi", "Mumbai", "Maharashtra", "Bangalore", "Chennai", "Kolkata", "Pune",
                "Jaipur", "Ahmedabad", "Lucknow", "Indore" };
        JComboBox comboBox = new JComboBox(items1);
        comboBox.setBounds(100, 100, 150, 27);
        centerAlignComboBox(comboBox);
        backgroundLabel.add(comboBox);

        String[] items2 = { "Hyderabad", "Delhi", "Mumbai", "Maharashtra", "Bangalore", "Chennai", "Kolkata", "Pune",
                "Jaipur", "Ahmedabad", "Lucknow", "Indore" };
        JComboBox comboBox_1 = new JComboBox(items2);
        comboBox_1.setBounds(390, 100, 150, 27);
        centerAlignComboBox(comboBox_1);
        backgroundLabel.add(comboBox_1);

        String[] items3 = { "Economy", "Business", "Premium" };
        JComboBox comboBox_2 = new JComboBox(items3);
        comboBox_2.setBounds(650, 100, 150, 27);
        centerAlignComboBox(comboBox_2);
        backgroundLabel.add(comboBox_2);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                src = (String) comboBox.getSelectedItem();
                dst = (String) comboBox_1.getSelectedItem();
                cls = (String) comboBox_2.getSelectedItem();

                try {
                    DbConn c = new DbConn();

                    String str = "SELECT * FROM Flight WHERE source = '" + src + "' AND destination = '" + dst
                            + "' AND class_code = '" + cls + "'";
                    ResultSet rs = c.stmt.executeQuery(str);
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null,
                                "No Flights between Source, Destination and Class");

                    } else {

                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnShow.setBounds(350, 150, 150, 30);
        backgroundLabel.add(btnShow);

        Book = new JLabel("ENTER FLIGHT CODE");
        Book.setFont(f2);
        Book.setForeground(Color.WHITE);
        Book.setBounds(150, 200, 180, 30);
        backgroundLabel.add(Book);

        pay = new JButton("Book Ticket");
        pay.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Changed font family and made it bold
        pay.setBackground(Color.ORANGE);
        pay.setForeground(Color.WHITE);

        pay.setBounds(550, 200, 150, 30);
        backgroundLabel.add(pay);

        textField = new TextField();
        textField.setBounds(350, 200, 150, 30);
        backgroundLabel.add(textField);

        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try {
                    DbConn c = new DbConn();
                    String str1 = "SELECT COUNT(*) AS count FROM flight WHERE flight_code = ?";
                    PreparedStatement pstmt = c.conn.prepareStatement(str1);
                    pstmt.setString(1, textField.getText());
                    ResultSet rs = pstmt.executeQuery();

                    rs.next();
                    int count = rs.getInt("count");

                    if (count > 0) {
                        new BookTicket(p.getUsername(),src, dst, cls, textField.getText(), p);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid flight code.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(23, 250, 800, 300);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(23, 250, 800, 300);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        backButton = new JButton("Back"); // Creating the back button
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Changed font family and made it bold
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(400, 620, 100, 30);
        backgroundLabel.add(backButton);

        // Adding ActionListener to the back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
//                new PassengerDashBoard(new PassengerInfo()).setVisible(true); // Open the PassengerMainFrame
                dispose(); // Close the current window
            }
        });

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 650);
        setVisible(true);
        setLocation(200, 50);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    private void centerAlignComboBox(JComboBox comboBox) {
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(renderer);
    }
}
