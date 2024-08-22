package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BookTicket extends JFrame {
    
    JTextField tf, tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    private String flight_amount;
    private String Date;
    private String Time;
    
    BookTicket(String username, String src, String dst, String cls, String flight, GetNewPassengerInfo p) {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        
        setTitle("Book Ticket");
        
        // Load background image
        ImageIcon background = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        getContentPane().add(backgroundLabel);
        
        JPanel panel = new JPanel(null); // Set layout to null for manual component positioning
        panel.setOpaque(false); // Make the panel transparent
        panel.setPreferredSize(new Dimension(background.getIconWidth(), background.getIconHeight()));
        backgroundLabel.add(panel);
        
        JLabel Payment = new JLabel("Book Ticket By Payment");
        Payment.setForeground(Color.WHITE);
        Payment.setFont(new Font("Tahoma", Font.PLAIN, 31));
        Payment.setBounds(450, 24, 442, 35);
        backgroundLabel.add(Payment);
        
        JLabel Username = new JLabel("UserName :");
        Username.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Username.setForeground(Color.WHITE);
        Username.setBounds(60, 80, 150, 27);
        backgroundLabel.add(Username);
        
        JLabel tf = new JLabel(username);
        tf.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf.setForeground(Color.WHITE);
        tf.setBounds(230, 80, 150, 27);
        backgroundLabel.add(tf);
        
        JLabel Flightcode = new JLabel("FLIGHT CODE :");
        Flightcode.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Flightcode.setForeground(Color.WHITE);
        Flightcode.setBounds(60, 120, 150, 27);
        backgroundLabel.add(Flightcode);
        
        JLabel tf1 = new JLabel(flight);
        tf1.setBounds(230, 120, 150, 27);
        tf1.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf1.setForeground(Color.WHITE);
        backgroundLabel.add(tf1);
        
        JLabel Source = new JLabel("Source :");
        Source.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Source.setForeground(Color.WHITE);
        Source.setBounds(60, 170, 150, 27);
        backgroundLabel.add(Source);
        
        JLabel tf2 = new JLabel(src);
        tf2.setBounds(230, 170, 150, 27);
        tf2.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf2.setForeground(Color.WHITE);
        backgroundLabel.add(tf2);
        
        JLabel Destination = new JLabel("Destination :");
        Destination.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Destination.setForeground(Color.WHITE);
        Destination.setBounds(60, 220, 150, 27);
        backgroundLabel.add(Destination);
        
        JLabel tf3 = new JLabel(dst);
        tf3.setBounds(230, 220, 150, 27);
        tf3.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf3.setForeground(Color.WHITE);
        backgroundLabel.add(tf3);
        
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plus(5, ChronoUnit.DAYS);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date = futureDate.format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        Time = LocalDateTime.now().format(timeFormatter);
        
        JLabel timeLabel = new JLabel("Journey Time :");
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(60, 270, 150, 27);
        backgroundLabel.add(timeLabel);
        
        JLabel tf4 = new JLabel(Time);
        tf4.setBounds(230, 270, 150, 27);
        tf4.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf4.setForeground(Color.WHITE);
        backgroundLabel.add(tf4);
        
        JLabel dateLabel = new JLabel("Journey Date :");
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(60, 320, 150, 27);
        backgroundLabel.add(dateLabel);
        
        JLabel tf5 = new JLabel(Date);
        tf5.setBounds(230, 320, 150, 27);
        tf5.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf5.setForeground(Color.WHITE);
        backgroundLabel.add(tf5);
        
        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(60, 370, 150, 27);
        backgroundLabel.add(amountLabel);
        
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
            DbConn c = new DbConn();
            String str = "SELECT amount FROM flight WHERE flight_code = ? AND source = ? AND destination = ? AND class_code = ?";
            PreparedStatement pstmt = c.conn.prepareStatement(str);
            pstmt.setString(1, flight);
            pstmt.setString(2, src);
            pstmt.setString(3, dst);
            pstmt.setString(4, cls);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                flight_amount = rs.getString("amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel tf6 = new JLabel(flight_amount);
        tf6.setBounds(230, 370, 150, 27);
        tf6.setFont(new Font("Tahoma", Font.BOLD, 20));
        tf6.setForeground(Color.WHITE);
        backgroundLabel.add(tf6);
        
        JLabel Camount = new JLabel("Confirm Amount :");
        Camount.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Camount.setForeground(Color.WHITE);
        Camount.setBounds(60, 420, 150, 27);
        backgroundLabel.add(Camount);
        
        JTextField tf7 = new JTextField();
        tf7.setBounds(230, 420, 150, 27);
        tf7.setFont(new Font("Tahoma", Font.BOLD, 20));
        backgroundLabel.add(tf7);
        
        JButton b1_2 = new JButton("Cancel");
        b1_2.setFont(new Font("Bree Serif", Font.BOLD, 20));
        b1_2.setBounds(60, 480, 150, 30);
        b1_2.setForeground(Color.WHITE);
        b1_2.setBackground(Color.RED);
        backgroundLabel.add(b1_2);
        
        JButton pay = new JButton("Book");
        pay.setFont(new Font("Bree Serif", Font.BOLD, 20));
        pay.setBounds(230, 480, 150, 30);
        pay.setBackground(Color.GREEN);
        pay.setForeground(Color.WHITE);
        backgroundLabel.add(pay);
        
        JButton back = new JButton("Back");
        back.setFont(new Font("Bree Serif", Font.BOLD, 20));
        back.setBounds(130, 550, 150, 30);
        back.setBackground(Color.BLUE); // Light Red
        back.setForeground(Color.WHITE); // White text
        backgroundLabel.add(back);
        
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
//                new FlightRoutes(new PassengerInfo(), "");
                dispose();
            }
        });
        
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String confirm = tf7.getText();
                if (confirm.isEmpty() || !flight_amount.equals(confirm)) {
                    JOptionPane.showMessageDialog(null, "Please Confirm the amount Correctly!");
                } else {
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to book the ticket?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            DbConn c = new DbConn();
                            String reserve = "INSERT INTO Reservation (username, flight_code, source, destination, time, date, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement pstmt = c.conn.prepareStatement(reserve);
                            pstmt.setString(1, p.getUsername());
                            pstmt.setString(2, flight);
                            pstmt.setString(3, src);
                            pstmt.setString(4, dst);
                            pstmt.setString(5, Time);
                            pstmt.setString(6, Date); 
                            pstmt.setString(7, flight_amount);

                            String insertPaymentQuery = "INSERT INTO Payment (username, flight_code, pay_date, source, destination, time, date, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement pstmt1 = c.conn.prepareStatement(insertPaymentQuery);
                            pstmt1.setString(1, p.getUsername());
                            pstmt1.setString(2, flight);
                            pstmt1.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                            pstmt1.setString(4, src);
                            pstmt1.setString(5, dst);
                            pstmt1.setString(6, Time);
                            pstmt1.setString(7, Date);
                            pstmt1.setString(8, flight_amount);

                            int reservationInserted = pstmt.executeUpdate();
                            int paymentInserted = pstmt1.executeUpdate();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "Ticket booked successfully!");
                        new ReservationInfo(p);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ticket booking canceled.");
                        dispose();
                    }
                }
            }
        });
        
        b1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PassengerDashBoard(new GetNewPassengerInfo());
                dispose();
            }
        });
        
        setSize(background.getIconWidth(), background.getIconHeight());
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame
        
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new BookTicket("", "Hyd", "Delhi", "Econ", "", new GetNewPassengerInfo());
    }
}
