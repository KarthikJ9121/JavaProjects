package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeletePassenger extends JFrame{  

    private JTable table;
    private JTextField textField;
    JLabel Book, Class;
    JButton Show, pay, backButton; // Added backButton
    Font f2;
    String src, dst, cls;
    
    public static void main(String[] args){		 
    	new DeletePassenger();    
    }
    
   
    
    public DeletePassenger(){
    	
    	setTitle("Delete Passenger");
    	
    	f2 = new Font("TimesRoman", Font.BOLD, 15);
    	Font f3 = new Font("TimesRoman", Font.PLAIN, 15);
	    getContentPane().setBackground(Color.WHITE);
	    getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(860,523);
		setLayout(null);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);  
		
		// Background image
		ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
		
		JLabel FlightDetails = new JLabel("Delete Passenger");
		FlightDetails.setFont(new Font("Bree Serif", Font.PLAIN, 31));
		FlightDetails.setForeground(Color.WHITE); // Thick Orange
		FlightDetails.setBounds(350, 50, 570, 35);
		backgroundLabel.add(FlightDetails);
		
		Book =  new JLabel("ENTER PASSENGER USERNAME : ");
		Book.setFont(f2);
		Book.setForeground(Color.WHITE);
		
		
		textField = new JTextField();
		textField.setFont(f3);
		
		pay = new JButton("Delete Passenger");
		pay.setFont(new Font("Bree Serif", Font.PLAIN, 20));
		pay.setBackground(Color.RED);;
		pay.setForeground(Color.WHITE);
		
		Book.setBounds(60, 120, 250, 30);
		backgroundLabel.add(Book);
		
		pay.setBounds(600, 120, 200, 30);
		backgroundLabel.add(pay);
		
		textField.setBounds(350, 120, 200, 30);
		backgroundLabel.add(textField);
		
		table = new JTable();
 	    table.setBackground(Color.WHITE);
 		table.setBounds(23, 190, 840, 400);
 	        
 		JScrollPane pane = new JScrollPane(table);
 		pane.setBounds(23, 190, 840, 400);
 	    pane.setBackground(Color.WHITE);
 	    backgroundLabel.add(pane);
 	    
 	    // Back button
 	    backButton = new JButton("Back");
 	    backButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
 	    backButton.setBackground(Color.BLUE);
 	    backButton.setForeground(Color.WHITE);
 	    backButton.setBounds(400, 620, 80, 30);
 	    backgroundLabel.add(backButton);
 	    backButton.addActionListener(new ActionListener() {
 	        public void actionPerformed(ActionEvent e) {
 	            dispose(); // Close the current frame
// 	            new AdminDashBoard("").setVisible(true); // Open the AdminMain frame
 	            dispose();
 	        }
 	    });
 	    
 	   setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(900,650);
	    setVisible(true);
	    setLocation(200,50);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setVisible(true);  
			
		try {
            DbConn c = new DbConn();
            
            String str = "SELECT * FROM passenger";
            ResultSet rs = c.stmt.executeQuery(str);
            if(!rs.isBeforeFirst()) {
            	JOptionPane.showMessageDialog(
        		    null,
        		    "No passengers registered in the system",
        		    "No Passengers Found",
        		    JOptionPane.INFORMATION_MESSAGE
        		);

            } 
            else {
            	table.setModel(DbUtils.resultSetToTableModel(rs));
            }
     
            
        }catch(SQLException e){
            e.printStackTrace();
        }
	 
		
		
		
		pay.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	        	 
	        	

	        	try {
	        		DbConn c = new DbConn();
	        		String str1 = "SELECT COUNT(*) AS count FROM Passenger WHERE username = ?";
	        	    PreparedStatement pstmt = c.conn.prepareStatement(str1);
	        	    pstmt.setString(1, textField.getText());
	        	    ResultSet rs = pstmt.executeQuery();

	        	    rs.next();
	        	    int count = rs.getInt("count");
	        	    
	        	    

	        	    
	        	    if (count > 0) {
	        	    	String username = textField.getText(); 
	        	    	try {
	        	    	    // Disable foreign key checks temporarily to perform delete operations
	        	    	    String disable = "SET FOREIGN_KEY_CHECKS = 0";
	        	    	    Statement dstmt = c.conn.createStatement();
	        	    	    dstmt.executeUpdate(disable);

	        	    	    // Delete from Reservation table
	        	    	    String reserveQuery = "DELETE FROM Reservation WHERE username = ?";
	        	    	    PreparedStatement reserveStmt = c.conn.prepareStatement(reserveQuery);
	        	    	    reserveStmt.setString(1, username);
	        	    	    reserveStmt.executeUpdate();

	        	    	    // Delete from Cancellation table
	        	    	    String cancelQuery = "DELETE FROM Cancellation WHERE username = ?";
	        	    	    PreparedStatement cancelStmt = c.conn.prepareStatement(cancelQuery);
	        	    	    cancelStmt.setString(1, username);
	        	    	    cancelStmt.executeUpdate();

	        	    	    // Delete from Payment table
	        	    	    String paymentQuery = "DELETE FROM Payment WHERE username = ?";
	        	    	    PreparedStatement paymentStmt = c.conn.prepareStatement(paymentQuery);
	        	    	    paymentStmt.setString(1, username);
	        	    	    paymentStmt.executeUpdate();

	        	    	   
	        	    	    //Delete from login
	        	    	    String loginQuery = "DELETE FROM PassengerLogin WHERE username = ?";
	        	    	    PreparedStatement loginStmt = c.conn.prepareStatement(loginQuery);
	        	    	    loginStmt.setString(1, username);
	        	    	    loginStmt.executeUpdate();

	        	    	    // Delete from Passenger table
	        	    	    String passengerQuery = "DELETE FROM Passenger WHERE username = ?";
	        	    	    PreparedStatement passengerDelete = c.conn.prepareStatement(passengerQuery);
	        	    	    passengerDelete.setString(1, username);
	        	    	    passengerDelete.executeUpdate();

	        	    	    // Enable foreign key checks after deletion
	        	    	    String enableFKChecksQuery = "SET FOREIGN_KEY_CHECKS = 1";
	        	    	    Statement enableFKChecksStmt = c.conn.createStatement();
	        	    	    enableFKChecksStmt.executeUpdate(enableFKChecksQuery);
	        	    	    
	        	    	    JOptionPane.showMessageDialog(null, "Passenger deleted successfully.");
	        	    	    dispose();
	        	    	    new DeletePassenger();
	        	    	    
	        	    	} catch (SQLException e) {
	        	    	    e.printStackTrace();
	        	    	}

	        	    	           	        	    	
	        	    } else {	            	        	     
	        	    	JOptionPane.showMessageDialog(null, "Please enter a valid Username.");
	        	    }

	
	        	} catch (Exception e) {
	        	    e.printStackTrace();
	        	}
	        	
	        }
	    
	        
		});  
		  	
    }
    
	
}
