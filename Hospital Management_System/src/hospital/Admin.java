package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.*;

public class Admin extends JFrame {

    public Admin(AdminLogin admin) {
        String username = admin.getUsername(); // Get the username from AdminLogin
        setTitle("Admin");

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        add(backgroundLabel);

        // Add image
        ImageIcon i1 = new ImageIcon("hospitalImages/admin.png");
        JLabel image = new JLabel(i1);
        image.setBounds(450, 120, 1000, 600);
        backgroundLabel.add(image);

        // Admin details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setOpaque(false);
        detailsPanel.setBounds(150, 100, 500, 400);
        backgroundLabel.add(detailsPanel);

        // Title
        JLabel titleLabel = new JLabel("Admin");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(250, 70, 300, 30);
        backgroundLabel.add(titleLabel);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 50, 100, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(nameLabel);

        JLabel nameValue = new JLabel();
        nameValue.setForeground(Color.WHITE);
        nameValue.setBounds(170, 50, 300, 30);
        nameValue.setFont(new Font("Arial", Font.PLAIN, 18));
        detailsPanel.add(nameValue);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 90, 100, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(emailLabel);

        JLabel emailValue = new JLabel();
        emailValue.setForeground(Color.WHITE);
        emailValue.setBounds(170, 90, 300, 30);
        emailValue.setFont(new Font("Arial", Font.PLAIN, 18));
        emailValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsPanel.add(emailValue);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(50, 130, 100, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(phoneLabel);

        JLabel phoneValue = new JLabel();
        phoneValue.setForeground(Color.WHITE);
        phoneValue.setBounds(170, 130, 300, 30);
        phoneValue.setFont(new Font("Arial", Font.PLAIN, 18));
        detailsPanel.add(phoneValue);

        // GitHub
        JLabel githubLabel = new JLabel("GitHub:");
        githubLabel.setForeground(Color.WHITE);
        githubLabel.setBounds(50, 170, 100, 30);
        githubLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(githubLabel);

        JLabel githubValue = new JLabel();
        githubValue.setForeground(Color.WHITE);
        githubValue.setBounds(170, 170, 300, 30);
        githubValue.setFont(new Font("Arial", Font.PLAIN, 18));
        githubValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsPanel.add(githubValue);

        // LinkedIn
        JLabel linkedinLabel = new JLabel("LinkedIn:");
        linkedinLabel.setForeground(Color.WHITE);
        linkedinLabel.setBounds(50, 210, 100, 30);
        linkedinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(linkedinLabel);

        JLabel linkedinValue = new JLabel();
        linkedinValue.setForeground(Color.WHITE);
        linkedinValue.setBounds(170, 210, 300, 30);
        linkedinValue.setFont(new Font("Arial", Font.PLAIN, 18));
        linkedinValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsPanel.add(linkedinValue);

        // Instagram
        JLabel instagramLabel = new JLabel("Instagram:");
        instagramLabel.setForeground(Color.WHITE);
        instagramLabel.setBounds(50, 250, 100, 30);
        instagramLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(instagramLabel);

        JLabel instagramValue = new JLabel();
        instagramValue.setForeground(Color.WHITE);
        instagramValue.setBounds(170, 250, 300, 30);
        instagramValue.setFont(new Font("Arial", Font.PLAIN, 18));
        instagramValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsPanel.add(instagramValue);

        // Twitter/X
        JLabel twitterLabel = new JLabel("Twitter/X:");
        twitterLabel.setForeground(Color.WHITE);
        twitterLabel.setBounds(50, 290, 100, 30);
        twitterLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detailsPanel.add(twitterLabel);

        JLabel twitterValue = new JLabel();
        twitterValue.setForeground(Color.WHITE);
        twitterValue.setBounds(170, 290, 300, 30);
        twitterValue.setFont(new Font("Arial", Font.PLAIN, 18));
        twitterValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsPanel.add(twitterValue);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        backButton.setBounds(200, 600, 150, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(e -> {
            new AdminDashboard(admin);
            dispose();
        });

        // Add mouse listeners to make links clickable
        addLinkMouseListener(emailValue, "mailto:jodukarthik25072@gmail.com");
        addLinkMouseListener(githubValue, "https://github.com/KarthikJ9121/");
        addLinkMouseListener(linkedinValue, "https://linkedin.com/in/karthik");
        addLinkMouseListener(instagramValue, "https://www.instagram.com/merciful_s_servant");
        addLinkMouseListener(twitterValue, "https://x.com/KarthikJodu");

        // Fetch and populate admin details
        fetchAdminDetails(username, nameValue, emailValue, phoneValue, githubValue, linkedinValue, instagramValue, twitterValue);

        // Set frame properties
        setSize(1000, 650);
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addLinkMouseListener(JLabel label, String url) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void fetchAdminDetails(String username, JLabel nameValue, JLabel emailValue, JLabel phoneValue, JLabel githubValue, JLabel linkedinValue, JLabel instagramValue, JLabel twitterValue) {
        try {
            DbConn dbConn = new DbConn();
            String query = "SELECT * FROM admin WHERE username = ?";
            PreparedStatement pstmt = dbConn.conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nameValue.setText(rs.getString("username"));
                emailValue.setText(String.format("<html><a href='mailto:%s'>%s</a></html>", rs.getString("email"), rs.getString("email")));
                phoneValue.setText(rs.getString("phone"));
                githubValue.setText(String.format("<html><a href='%s'>%s</a></html>", rs.getString("github_url"),rs.getString("github_url")));
                linkedinValue.setText(String.format("<html><a href='%s'>%s</a></html>", rs.getString("linkedin_url"), rs.getString("linkedin_url")));
                instagramValue.setText(String.format("<html><a href='%s'>%s</a></html>", rs.getString("instagram_url"), rs.getString("instagram_url")));
                twitterValue.setText(String.format("<html><a href='%s'>%s</a></html>", rs.getString("twitter_url"),  rs.getString("twitter_url")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching admin details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Admin(new AdminLogin());
    }
}
