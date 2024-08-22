package airline;

import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {

    public Admin() {
        setTitle("Admin");
        setLayout(null); // Use null layout for absolute positioning

        // Set background image
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air8.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        add(backgroundLabel);

        // Add ADMIN label
        JLabel adminLabel = new JLabel("ADMIN");
        adminLabel.setBounds(280, 200, 300, 50);
        adminLabel.setFont(new Font("Bree Serif", Font.BOLD, 36));
        adminLabel.setForeground(Color.black);
        backgroundLabel.add(adminLabel);

        // Add admin details labels
        Font labelFont = new Font("Bree Serif", Font.BOLD, 20);

        JLabel nameLabel = new JLabel("Name:   Admin ");
        nameLabel.setBounds(200, 280, 400, 30);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.black);

        JLabel emailLabel = new JLabel("Email:   admin123@gmail.com");
        emailLabel.setBounds(200, 320, 400, 30);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.black);

        JLabel phoneLabel = new JLabel("Phone No:   9876543210");
        phoneLabel.setBounds(200, 360, 400, 30);
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(Color.black);

        JLabel instagramLabel = new JLabel("Instagram:   https://instagram.com/admin123");
        instagramLabel.setBounds(200, 400, 500, 30);
        instagramLabel.setFont(labelFont);
        instagramLabel.setForeground(Color.black);

        JLabel linkedinLabel = new JLabel("LinkedIn:   https://linkedin.com/admin123");
        linkedinLabel.setBounds(200, 440, 400, 30);
        linkedinLabel.setFont(labelFont);
        linkedinLabel.setForeground(Color.black);

        JLabel twitterLabel = new JLabel("Twitter:   https://twitter.com/admin123");
        twitterLabel.setBounds(200, 480, 400, 30);
        twitterLabel.setFont(labelFont);
        twitterLabel.setForeground(Color.black);

        JLabel githubLabel = new JLabel("GitHub:   https://github.com/admin123");
        githubLabel.setBounds(200, 520, 400, 30);
        githubLabel.setFont(labelFont);
        githubLabel.setForeground(Color.black);

        // Add the labels to the background label
        backgroundLabel.add(nameLabel);
        backgroundLabel.add(emailLabel);
        backgroundLabel.add(phoneLabel);
        backgroundLabel.add(instagramLabel);
        backgroundLabel.add(linkedinLabel);
        backgroundLabel.add(twitterLabel);
        backgroundLabel.add(githubLabel);

        // Set frame properties
        setSize(1000, 650);
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new Admin();
    }
}
