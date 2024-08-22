package airline;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel contentPanel;
    private PassengerLogin obj;

    public Login() {
    	
    	setTitle("Login");
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // Load background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-20, 0 , 1400, 900); // Set bounds to cover the entire frame
        add(backgroundLabel);
        
        contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Make the panel transparent
        contentPanel.setLayout(null);
        backgroundLabel.add(contentPanel);

        JLabel lblWelcome = new JLabel("Welcome To Indian AirWays, Your home is in the Sky.\n please add emaiSender, seatingInfo, payments.\n flight locatn");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Bree Serif", Font.BOLD, 30)); // Set font to Bree Serif and make it bold
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        lblWelcome.setBounds(280, 75, 800, 100);
        backgroundLabel.add(lblWelcome);

        JLabel lblLoginType = new JLabel("Select Your Login Type");
        lblLoginType.setFont(new Font("Bree Serif", Font.BOLD, 30)); // Set font to Bree Serif and make it bold
        lblLoginType.setForeground(Color.WHITE);
        lblLoginType.setHorizontalAlignment(JLabel.CENTER);
        lblLoginType.setBounds(330, 165, 700, 30);
        backgroundLabel.add(lblLoginType);

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setBackground(Color.GREEN);
        btnAdmin.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Set font to Bree Serif and make it bold
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminLogin adminLogin = new AdminLogin();
                dispose();
            }
        });
        btnAdmin.setBounds(580, 220, 200, 30);
        backgroundLabel.add(btnAdmin);

        JButton btnPassenger = new JButton("Passenger");
        btnPassenger.setForeground(Color.WHITE);
        btnPassenger.setBackground(Color.ORANGE);
        btnPassenger.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Set font to Bree Serif and make it bold
        btnPassenger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obj = new PassengerLogin();
                dispose();
            }
        });
        
        btnPassenger.setBounds(580, 300, 200, 30);
        backgroundLabel.add(btnPassenger);

        JButton btnBack = new JButton("Cancel");
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.RED);
        btnBack.setFont(new Font("Bree Serif", Font.BOLD, 20)); // Set font to Bree Serif and make it bold
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBack.setBounds(630, 500, 100, 30);
        backgroundLabel.add(btnBack);
        
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        Login frame = new Login();
    }
}
