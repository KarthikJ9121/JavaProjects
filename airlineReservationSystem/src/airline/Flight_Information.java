package airline;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Flight_Information extends JFrame { // Second Frame

    private JTable table;
    private JTextField textField;

    public static void main(String[] args) {
        new Flight_Information().setVisible(true);
    }

    public Flight_Information() {
    	
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

        JLabel Fcode = new JLabel("FLIGHT CODE");
        Fcode.setFont(new Font("Bree Serif", Font.BOLD, 17));
        Fcode.setForeground(Color.WHITE);
        Fcode.setBounds(50, 100, 200, 30);
        backgroundLabel.add(Fcode);

        JLabel FlightDetails = new JLabel("FLIGHT INFORMATION");
        FlightDetails.setFont(new Font("Bree Serif", Font.BOLD, 31));
        FlightDetails.setForeground(Color.WHITE);
        FlightDetails.setBounds(50, 20, 570, 35);
        backgroundLabel.add(FlightDetails);

        JButton btnShow = new JButton("SHOW");
        btnShow.setFont(new Font("Bree Serif", Font.BOLD, 20));
        btnShow.setBackground(Color.GREEN);
        btnShow.setForeground(Color.WHITE);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String code = textField.getText();

                try {
                    DbConn c = new DbConn();
                    String str = "select f_code,f_name,src,dst,capacity,class_code,class_name from flight, sector where f_code = '"
                            + code + "'";

                    ResultSet rs = c.stmt.executeQuery(str);
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnShow.setBounds(220, 150, 120, 30);
        backgroundLabel.add(btnShow);

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setBounds(23, 250, 800, 300);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(23, 250, 800, 300);
        pane.setBackground(Color.WHITE);
        backgroundLabel.add(pane);

        textField = new JTextField();
        textField.setBounds(220, 100, 200, 30);
        backgroundLabel.add(textField);

        JLabel FlightCode = new JLabel("FLIGHT CODE");
        FlightCode.setFont(new Font("Bree Serif", Font.BOLD, 13));
        FlightCode.setForeground(Color.WHITE);
        FlightCode.setBounds(23, 220, 126, 14);
        backgroundLabel.add(FlightCode);

        JLabel FlightName = new JLabel("FLIGHT NAME");
        FlightName.setFont(new Font("Bree Serif", Font.BOLD, 13));
        FlightName.setForeground(Color.WHITE);
        FlightName.setBounds(145, 220, 120, 14);
        backgroundLabel.add(FlightName);

        JLabel Source = new JLabel("SOURCE");
        Source.setFont(new Font("Bree Serif", Font.BOLD, 13));
        Source.setForeground(Color.WHITE);
        Source.setBounds(275, 220, 104, 14);
        backgroundLabel.add(Source);

        JLabel Destination = new JLabel("DESTINATION");
        Destination.setFont(new Font("Bree Serif", Font.BOLD, 13));
        Destination.setForeground(Color.WHITE);
        Destination.setBounds(367, 220, 120, 14);
        backgroundLabel.add(Destination);

        JLabel Capacity = new JLabel("CAPACITY");
        Capacity.setFont(new Font("Bree Serif", Font.BOLD, 13));
        Capacity.setForeground(Color.WHITE);
        Capacity.setBounds(497, 220, 111, 14);
        backgroundLabel.add(Capacity);

        JLabel ClassCode = new JLabel("CLASS CODE");
        ClassCode.setFont(new Font("Bree Serif", Font.BOLD, 13));
        ClassCode.setForeground(Color.WHITE);
        ClassCode.setBounds(587, 220, 120, 14);
        backgroundLabel.add(ClassCode);

        JLabel ClassName = new JLabel("CLASS NAME");
        ClassName.setFont(new Font("Bree Serif", Font.BOLD, 13));
        ClassName.setForeground(Color.WHITE);
        ClassName.setBounds(700, 220, 111, 14);
        backgroundLabel.add(ClassName);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 650);
        setVisible(true);
        setLocation(200, 50);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
}
