package airline;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PassengerDetail extends JFrame {

    JTextField tf, tf1, tf2, tf3, tf4, tf5, tf6, tf7;

    public PassengerDetail(GetNewPassengerInfo p) {

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);

        setTitle("PASSENGER DETAILS");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setLayout(null);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        JLabel username = new JLabel("Username: ");
        username.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        username.setForeground(Color.WHITE); // Change font color to white
        username.setBounds(60, 80, 150, 27);
        backgroundLabel.add(username);

        JLabel tf = new JLabel(p.getUsername());
        tf.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf.setForeground(Color.WHITE); // Change font color to white
        tf.setBounds(180, 80, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        name.setForeground(Color.WHITE); // Change font color to white
        name.setBounds(60, 120, 150, 27);
        backgroundLabel.add(name);

        JLabel tf1 = new JLabel(p.getName());
        tf1.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf1.setForeground(Color.WHITE); // Change font color to white
        tf1.setBounds(180, 120, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf1);

        JLabel Gmail = new JLabel("Gmail: ");
        Gmail.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Gmail.setForeground(Color.WHITE); // Change font color to white
        Gmail.setBounds(60, 170, 150, 27);
        backgroundLabel.add(Gmail);

        JLabel tf2 = new JLabel(p.getGmail());
        tf2.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf2.setForeground(Color.WHITE); // Change font color to white
        tf2.setBounds(180, 170, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf2);

        JLabel Gender = new JLabel("Gender: ");
        Gender.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Gender.setForeground(Color.WHITE); // Change font color to white
        Gender.setBounds(60, 220, 150, 27);
        backgroundLabel.add(Gender);

        JLabel tf3 = new JLabel(p.getGender());
        tf3.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf3.setForeground(Color.WHITE); // Change font color to white
        tf3.setBounds(180, 220, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf3);

        JLabel Nationality = new JLabel("Nationality: ");
        Nationality.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Nationality.setForeground(Color.WHITE); // Change font color to white
        Nationality.setBounds(60, 270, 150, 27);
        backgroundLabel.add(Nationality);

        JLabel tf4 = new JLabel(p.getNationality());
        tf4.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf4.setForeground(Color.WHITE); // Change font color to white
        tf4.setBounds(180, 270, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf4);

        JLabel Passport = new JLabel("Passport: ");
        Passport.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Passport.setForeground(Color.WHITE); // Change font color to white
        Passport.setBounds(60, 320, 150, 27);
        backgroundLabel.add(Passport);

        JLabel tf7 = new JLabel(p.getPassport_no());
        tf7.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf7.setForeground(Color.WHITE); // Change font color to white
        tf7.setBounds(180, 320, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf7);

        JLabel Phno = new JLabel("PH NO: ");
        Phno.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Phno.setForeground(Color.WHITE); // Change font color to white
        Phno.setBounds(60, 370, 150, 27);
        backgroundLabel.add(Phno);

        JLabel tf5 = new JLabel(p.getPhonenumber());
        tf5.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf5.setForeground(Color.WHITE); // Change font color to white
        tf5.setBounds(160, 370, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf5);

        JLabel AddPassengers = new JLabel("Your Credentials");
        AddPassengers.setForeground(Color.WHITE);
        AddPassengers.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddPassengers.setBounds(150, 24, 442, 35);
        backgroundLabel.add(AddPassengers);

        JLabel Address = new JLabel("Address: ");
        Address.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        Address.setForeground(Color.WHITE); // Change font color to white
        Address.setBounds(60, 420, 150, 27);
        backgroundLabel.add(Address);

        JLabel tf6 = new JLabel(p.getAddress());
        tf6.setFont(new Font("Tahoma", Font.BOLD, 17)); // Bold font
        tf6.setForeground(Color.WHITE); // Change font color to white
        tf6.setBounds(180, 420, 200, 27); // Increment x-axis by 100
        backgroundLabel.add(tf6);

        JButton backButton = new JButton("Back"); // Add back button
        backButton.setBounds(60, 480, 100, 30);
        backButton.setBackground(Color.BLUE);  
        backButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Increase font size and bold
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to redirect to passengerMainFrame
//                new PassengerDashBoard(new PassengerInfo()).setVisible(true); // Redirect to PassengerMainFrame
                dispose(); // Close current frame
            }
        });

        JButton editButton = new JButton("Edit Details");
        editButton.setBounds(200, 480, 150, 30);
        editButton.setBackground(Color.ORANGE);
        editButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        editButton.setForeground(Color.WHITE);
        backgroundLabel.add(editButton);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserDetailsEdit(p).setVisible(true);
                dispose();
            }
        });

        setVisible(true);
        setLocation(200, 100);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        setVisible(true);
        setLocation(200, 100);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PassengerDetail(new GetNewPassengerInfo());
    }
}
