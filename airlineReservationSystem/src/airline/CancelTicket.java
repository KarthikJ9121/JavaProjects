package airline;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CancelTicket extends JFrame {

    private JTextField textField_1, textField_2;
    private JTable table;

    public static void main(String[] args) {
        String username = "exampleUsername"; // Replace "exampleUsername" with an actual username
        new CancelTicket(new GetNewPassengerInfo());
    }

    public CancelTicket(GetNewPassengerInfo p) {
        initialize(p);
    }

    private void initialize( GetNewPassengerInfo p) {
        setTitle("Cancel Ticket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air8.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel Cancellation = new JLabel("Cancel Your Reserved Ticket");
        Cancellation.setForeground(Color.WHITE);
        Cancellation.setFont(new Font("Bree Serif", Font.BOLD, 31));
        Cancellation.setBounds(250, 54, 500, 38);
        backgroundLabel.add(Cancellation);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Bree Serif", Font.BOLD, 20));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(60, 150, 132, 26);
        backgroundLabel.add(lblUsername);

        JLabel lblFlightCode = new JLabel("Flight Code");
        lblFlightCode.setFont(new Font("Bree Serif", Font.BOLD, 20));
        lblFlightCode.setBounds(60, 200, 150, 27);
        lblFlightCode.setForeground(Color.WHITE);
        backgroundLabel.add(lblFlightCode);

        JButton btnProceed = new JButton("Proceed");
        btnProceed.setFont(new Font("Bree Serif", Font.BOLD, 20));
        btnProceed.setBackground(Color.GREEN);
        btnProceed.setForeground(Color.WHITE);
        btnProceed.setBounds(60, 290, 150, 30);
        backgroundLabel.add(btnProceed);

        textField_1 = new JTextField();
        textField_1.setBounds(250, 150, 150, 27);
        backgroundLabel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(250, 200, 150, 27);
        backgroundLabel.add(textField_2);
        
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(400, 120, 800, 400);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(520, 120, 800, 400);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);
        
        
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

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(250, 290, 150, 30);
        backgroundLabel.add(backButton);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        btnProceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = textField_1.getText();
                String flight_code = textField_2.getText();

                if (username.isEmpty() || flight_code.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                } else {
                    // Assuming DbConn class exists and connects to the database

					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this ticket?", "Confirmation", JOptionPane.YES_NO_OPTION);
	
					if(dialogResult == JOptionPane.YES_OPTION) {
	  					try {
	  					    // Step 1: Select reservation details
	  						DbConn c = new DbConn();
	  						
	  					    String reserve = "SELECT * FROM Reservation WHERE username = ? AND flight_code = ?";
	  					    PreparedStatement pstmt = c.conn.prepareStatement(reserve);
	  					    pstmt.setString(1, username);
	  					    pstmt.setString(2, flight_code);
	  					    ResultSet reservationResultSet = pstmt.executeQuery();
	
	  					    // Step 2: Insert into Cancellation table
	  					    
	  					    if (reservationResultSet.next()) {
	  					        String source = reservationResultSet.getString("source");
	  					        String destination = reservationResultSet.getString("destination");
	  					        String time = reservationResultSet.getString("time");
	  					        Date date = reservationResultSet.getDate("date");
	  					        double amount = reservationResultSet.getDouble("amount");
	
	  					        String insertCancellationQuery = "INSERT INTO Cancellation (username, flight_code, source, destination, time, date, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
	  					        PreparedStatement insertCancellationStmt = c.conn.prepareStatement(insertCancellationQuery);
	  					        insertCancellationStmt.setString(1, username);
	  					        insertCancellationStmt.setString(2, flight_code);
	  					        insertCancellationStmt.setString(3, source);
	  					        insertCancellationStmt.setString(4, destination);
	  					        insertCancellationStmt.setString(5, time);
	  					        insertCancellationStmt.setDate(6, date);
	  					        insertCancellationStmt.setDouble(7, amount);
	
	  					        int rowsInserted = insertCancellationStmt.executeUpdate();
	
	  					        // Step 3: Delete from Reservation table
	  					        if (rowsInserted > 0) {
	  					            String deleteReservationQuery = "DELETE FROM Reservation WHERE username = ? AND flight_code = ?";
	  					            PreparedStatement deleteReservationStmt = c.conn.prepareStatement(deleteReservationQuery);
	  					            deleteReservationStmt.setString(1, username);
	  					            deleteReservationStmt.setString(2, flight_code);
	
	  					            int rowsDeleted = deleteReservationStmt.executeUpdate();
	
	  					            if (rowsDeleted > 0) {
	  					            	JOptionPane.showMessageDialog(
  					            		    null,
  					            		    "Your ticket has been cancelled successfully!\nWe hope to see you again in the future.",
  					            		    "Ticket Cancelled",
  					            		    JOptionPane.INFORMATION_MESSAGE
  					            		    
  					            		);
//	  					            	new ReservationInfo(p);
	  					            	new CancellationInfo(p);
	  					            	dispose();
	  					            } else {
	  					            	JOptionPane.showMessageDialog(
  					            		    null,
  					            		    "We couldn't find any reservation for the given details.\nPlease ensure your username and flight code are correct.",
  					            		    "Cancellation Unsuccessful",
  					            		    JOptionPane.INFORMATION_MESSAGE
  					            		);

	  					            }
	  					        }
	  					    } else {
	  					    	JOptionPane.showMessageDialog(null, "No reservation found for the given username and flight code.", "Reservation Not Found", JOptionPane.INFORMATION_MESSAGE);
	  					    }
	  					    
	  					} catch (SQLException e) {
	  					    e.printStackTrace();
	  					}
	  				}

                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                new PassengerDashBoard(new PassengerInfo()).setVisible(true);
                dispose();
            }
        });

        setSize(860, 500);
        setVisible(true);
        setLocation(200, 130);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
    }
}
