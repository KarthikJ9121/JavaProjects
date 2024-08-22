package hospital;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel contentPanel;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 0, 0); // Set bounds to cover the entire frame
        add(backgroundLabel);
        
        contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Make the panel transparent
        contentPanel.setLayout(null);
        backgroundLabel.add(contentPanel);

        JLabel lblWelcome = new JLabel("Welcome to Hospital Management System !!");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial and make it bold
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        lblWelcome.setBounds(150, 100, 700, 50);
        backgroundLabel.add(lblWelcome);

        JLabel lblLoginType = new JLabel("Please Select Your Login Type");
        lblLoginType.setFont(new Font("Arial", Font.BOLD, 25)); // Set font to Arial and make it bold
        lblLoginType.setForeground(Color.WHITE);
        lblLoginType.setHorizontalAlignment(JLabel.CENTER);
        lblLoginType.setBounds(320, 200, 400, 30);
        backgroundLabel.add(lblLoginType);

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setBackground(Color.GREEN);
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to Arial and make it bold
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for admin login
                dispose(); // Close login window
                // Open admin login window
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.setVisible(true);
            }
        });
        btnAdmin.setBounds(250, 300, 200, 40);
        backgroundLabel.add(btnAdmin);

        JButton btnPatient = new JButton("Patient");
        btnPatient.setForeground(Color.WHITE);
        btnPatient.setBackground(Color.BLUE);
        btnPatient.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to Arial and make it bold
        btnPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for patient login
                dispose(); // Close login window
                // Open patient login window
                PatientLogin patientLogin = new PatientLogin();
                patientLogin.setVisible(true);
            }
        });
        btnPatient.setBounds(500, 300, 200, 40);
        backgroundLabel.add(btnPatient);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(Color.RED);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to Arial and make it bold
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for cancel button
                dispose(); // Close login window
            }
        });
        btnCancel.setBounds(370, 390, 200, 40);
        backgroundLabel.add(btnCancel);

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        Login frame = new Login();
    }
}
