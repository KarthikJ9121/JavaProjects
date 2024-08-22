package airline;
import airline.AdminDashBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddNewFlight extends JFrame {

    JTextField tf, tf1, tf2;
    JLabel Source, Destination, Class;
    Font boldFont;
    Font comboBoxFont;

    public AddNewFlight(AdminLogin admin) {

        boldFont = new Font("Bree Serif", Font.BOLD, 17);
        comboBoxFont = new Font("Bree Serif", Font.BOLD, 14);

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);

        setTitle("Add New Flight");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setLayout(null);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight()); // Decrease x-coordinate by 200
        getContentPane().add(backgroundLabel);

        JLabel flight_code = new JLabel("Flight Code");
        flight_code.setFont(boldFont);
        flight_code.setForeground(Color.white);
        flight_code.setBounds(100, 150, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(flight_code);

        tf = new JTextField();
        tf.setBounds(250, 150, 150, 27); // Decrease x-coordinate by 200
        tf.setFont(boldFont);
        backgroundLabel.add(tf);

        JButton Next = new JButton("Add Flight");
        Next.setBounds(100, 500, 150, 30); // Decrease x-coordinate by 200
        Next.setBackground(Color.GREEN);
        Next.setForeground(Color.WHITE);
        Next.setFont(boldFont);
        backgroundLabel.add(Next);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 500, 150, 30); // Decrease x-coordinate by 200
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(boldFont);
        backgroundLabel.add(backButton);
        
        JButton visitFlights = new JButton("SeeFlights");
        visitFlights.setBounds(200, 550, 150, 30); // Decrease x-coordinate by 200
        visitFlights.setBackground(Color.orange);
        visitFlights.setForeground(Color.WHITE);
        visitFlights.setFont(boldFont);
        backgroundLabel.add(visitFlights);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        visitFlights.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
            	new FlightInformation(admin);
            }
        });

        JLabel flight_name = new JLabel("Flight Name");
        flight_name.setFont(boldFont);
        flight_name.setForeground(Color.white);
        flight_name.setBounds(100, 200, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(flight_name);

        tf1 = new JTextField();
        tf1.setBounds(250, 200, 150, 27); // Decrease x-coordinate by 200
        tf1.setFont(boldFont);
        backgroundLabel.add(tf1);

        Source = new JLabel("Source");
        Source.setFont(boldFont);
        Source.setForeground(Color.white);
        Source.setBounds(100, 250, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(Source);

        String[] items1 = {"Select City", "Ahmedabad", "Bangalore", "Chennai", "Delhi", "Hyderabad", "Indore", "Jaipur", "Kolkata", "Lucknow", "Maharashtra", "Mumbai", "Pune"};
        JComboBox comboBox = new JComboBox(items1);
        comboBox.setBounds(250, 250, 150, 27); // Decrease x-coordinate by 200
        comboBox.setFont(comboBoxFont);
        backgroundLabel.add(comboBox);

        Destination = new JLabel("Destination");
        Destination.setFont(boldFont);
        Destination.setForeground(Color.white);
        Destination.setBounds(100, 300, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(Destination);

        String[] items2 = {"Select City", "Ahmedabad", "Bangalore", "Chennai", "Delhi", "Hyderabad", "Indore", "Jaipur", "Kolkata", "Lucknow", "Maharashtra", "Mumbai", "Pune"};
        JComboBox comboBox_1 = new JComboBox(items2);
        comboBox_1.setBounds(250, 300, 150, 27); // Decrease x-coordinate by 200
        comboBox_1.setFont(comboBoxFont);
        backgroundLabel.add(comboBox_1);

        Class = new JLabel("Class");
        Class.setFont(boldFont);
        Class.setForeground(Color.white);
        Class.setBounds(100, 350, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(Class);

        String[] items3 = {"Business", "Economy", "Premium"};
        JComboBox comboBox_2 = new JComboBox(items3);
        comboBox_2.setBounds(250, 350, 150, 27); // Decrease x-coordinate by 200
        comboBox_2.setFont(comboBoxFont);
        backgroundLabel.add(comboBox_2);

        JLabel costOfService = new JLabel("Cost of Service");
        costOfService.setFont(boldFont);
        costOfService.setForeground(Color.white);
        costOfService.setBounds(100, 400, 150, 27); // Decrease x-coordinate by 200
        backgroundLabel.add(costOfService);

        tf2 = new JTextField();
        tf2.setBounds(250, 400, 150, 27); // Decrease x-coordinate by 200
        tf2.setFont(boldFont);
        backgroundLabel.add(tf2);

        JLabel AddPassengers = new JLabel("ADD FLIGHT DETAILS");
        AddPassengers.setForeground(Color.WHITE);
        AddPassengers.setFont(new Font("Bree Serif", Font.BOLD, 31));
        AddPassengers.setBounds(100, 50, 442, 35); // Decrease x-coordinate by 200
        backgroundLabel.add(AddPassengers);

        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String flight_code = tf.getText();
                String flight_name = tf1.getText();
                String source = (String) comboBox.getSelectedItem();
                String destination = (String) comboBox_1.getSelectedItem();
                String classType = (String) comboBox_2.getSelectedItem();
                String amount = tf2.getText();

                if (source.isEmpty() || destination.isEmpty() || source.equals(destination) || classType.isEmpty() || amount.isEmpty() || flight_code.isEmpty() | flight_name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please, check all fields and Submit!");
                } else {
                    try {
                        DbConn c = new DbConn();
                        String str = "INSERT INTO flight values( '" + flight_code + "', '" + flight_name + "', '" + source + "','" + destination + "', '" + classType + "', '" + amount + "')";

                        c.stmt.executeUpdate(str);
                        JOptionPane.showMessageDialog(null, "Flight Added");

                        tf.setText(null);
                        tf1.setText(null);
                        tf2.setText(null);
                        comboBox.setSelectedIndex(0);
                        comboBox_1.setSelectedIndex(0);
                        comboBox_2.setSelectedIndex(0);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        setVisible(true);
        setLocation(200, 100);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public static void main(String[] args) {
        new AddNewFlight(new AdminLogin());
    }
}
