package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class NewPassenger extends JFrame {

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    public boolean isValidIndianPassport(String passportNumber) {
        // Updated regex: "Ind" followed by any 7 digits
        String regex = "[a-z \\ A-Z]{2}[0-9]{7}$";
        return passportNumber.matches(regex);
    }

    public boolean isUsernameAvailable(String username) {
        boolean available = false;

        try {
            DbConn conn = new DbConn();
            String checkQuery = "SELECT * FROM Passenger WHERE username = ?";
            PreparedStatement pstmt = conn.conn.prepareStatement(checkQuery);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                // Username is available if the result set is empty
                available = true;
            }

            // Close result set, statement, and connection
            rs.close();
            pstmt.close();
            conn.conn.close();

        } catch (SQLException e) {
            // Handle any SQL exceptions here
            e.printStackTrace();
        }

        return available;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    public NewPassenger() {
        // Image as Background
        ImageIcon backgroundImage = new ImageIcon("airlineImages/air4.jpg");
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(background);

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("New Passenger");

        JLabel AddPassengers = new JLabel("ENTER YOUR DETAILS");
        AddPassengers.setForeground(Color.WHITE);
        AddPassengers.setFont(new Font("Tahoma", Font.BOLD, 30));
        AddPassengers.setBounds(280, 34, 442, 35);
        background.add(AddPassengers);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778 + 240, 486);
        getContentPane().setLayout(null);

        JLabel Name = new JLabel("Name");
        Name.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        Name.setForeground(Color.WHITE);
        Name.setBounds(300 , 90, 150, 27); // Changed 240 to 200 here
        background.add(Name);

        JTextField nametf = new JTextField();
        nametf.setBounds(440 , 90, 150, 27); // Kept 240 here
        nametf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(nametf);

        JLabel email = new JLabel("E-Mail");
        email.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        email.setForeground(Color.WHITE);
        email.setBounds(300 , 140, 150, 27); // Changed 240 to 200 here
        background.add(email);

        JTextField emailtf = new JTextField();
        emailtf.setBounds(440, 140, 150, 27); // Kept 240 here
        emailtf.setFont(new Font("Bree Serif", Font.BOLD, 13)); // Bold and Bree Serif font
        background.add(emailtf);

        emailtf.setText("example@gmail.com"); // Placeholder text initially

        emailtf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailtf.getText().equals("example@gmail.com")) {
                    emailtf.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailtf.getText().isEmpty()) {
                    emailtf.setText("example@gmail.com");
                }
            }
        });

        JLabel Gender = new JLabel("Gender");
        Gender.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        Gender.setForeground(Color.WHITE);
        Gender.setBounds(300, 190, 150, 27); // Changed 240 to 200 here
        background.add(Gender);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(440, 190, 80, 27); // Kept 240 here
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(530, 190, 80, 27); // Kept 240 here

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        background.add(male);
        background.add(female);

        JLabel passport_no = new JLabel("Passport No.");
        passport_no.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        passport_no.setForeground(Color.WHITE);
        passport_no.setBounds(300, 240, 150, 27); // Changed 240 to 200 here
        background.add(passport_no);

        JTextField passport_notf = new JTextField();
        passport_notf.setBounds(440, 240, 150, 27); // Kept 240 here
        background.add(passport_notf);

        passport_notf.setText("Example : In1234567"); // Placeholder text initially

        passport_notf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passport_notf.getText().equals("Example : In1234567")) {
                    passport_notf.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passport_notf.getText().isEmpty()) {
                    passport_notf.setText("Example : In1234567");
                }
            }
        });

        JLabel nation = new JLabel("Nationality");
        nation.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        nation.setForeground(Color.WHITE);
        nation.setBounds(300, 290, 150, 27); // Changed 240 to 200 here
        background.add(nation);

        JTextField nationtf = new JTextField();
        nationtf.setBounds(440, 290, 150, 27); // Kept 240 here
        nationtf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(nationtf);

        JLabel address = new JLabel("Address");
        address.setBounds(300, 340, 150, 27);
        address.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        address.setForeground(Color.WHITE);
        background.add(address);

        JTextField addresstf = new JTextField();
        addresstf.setBounds(440, 340, 150, 27);
        addresstf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(addresstf);

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(300, 390, 150, 27);
        phone.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        phone.setForeground(Color.WHITE);
        background.add(phone);

        JTextField phonetf = new JTextField();
        phonetf.setBounds(440, 390, 150, 27);
        phonetf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(phonetf);

        JLabel user = new JLabel("Username");
        user.setBounds(300, 440, 150, 27);
        user.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        user.setForeground(Color.WHITE);
        background.add(user);

        JTextField usertf = new JTextField();
        usertf.setBounds(440, 440, 150, 27);
        usertf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(usertf);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 490, 150, 27);
        password.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        password.setForeground(Color.WHITE);
        background.add(password);

        JPasswordField passwordtf = new JPasswordField();
        passwordtf.setEchoChar('*');
        passwordtf.setBounds(440, 490, 150, 27);
        passwordtf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        background.add(passwordtf);

        JLabel confirm = new JLabel("Confirm Password");
        confirm.setBounds(290, 540, 150, 27);
        confirm.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        confirm.setForeground(Color.WHITE);
        background.add(confirm);

        JPasswordField confirmtf = new JPasswordField();
        confirmtf.setEchoChar('*');
        confirmtf.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        confirmtf.setBounds(440, 540, 150, 27);
        background.add(confirmtf);

        JButton save = new JButton("Save");
        save.setBounds(290, 590, 80, 27);
        save.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        save.setBackground(Color.GREEN);
        save.setForeground(Color.WHITE);
        background.add(save);

        JButton back = new JButton("Back");
        back.setBounds(460, 590, 100, 27);
        back.setFont(new Font("Bree Serif", Font.BOLD, 17)); // Bold and Bree Serif font
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        background.add(back);


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to passengerLogin
                new PassengerLogin();
                dispose();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nametf.getText();
                String email = emailtf.getText();
                String nation = nationtf.getText();
                String passport_no = passport_notf.getText();
                String address = addresstf.getText();
                String username = usertf.getText();

                char[] passwordChars = passwordtf.getPassword();
                String password = new String(passwordChars);

                String confirm = new String(confirmtf.getPassword()); // Fetch password from JPasswordField

                String ph = phonetf.getText();
                String gender = null;
                if (male.isSelected()) {
                    gender = "male";
                } else if (female.isSelected()) {
                    gender = "female";
                }

                if (confirm.isEmpty() || name.isEmpty() || email.isEmpty() || nation.isEmpty() || passport_no.isEmpty() || address.isEmpty() || gender == null || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                } else if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(null, "Confirm Password does not match the entered password!");
                } else if (!isValidPhoneNumber(ph)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Phone Number!");
                } else if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address!");
                } else if (!isValidIndianPassport(passport_no)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Indian passport number!");
                } else if (!isUsernameAvailable(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose another username!");
                } else {
                    try {
                        // Insert passenger data into the database
                        DbConn c = new DbConn();
                        String insertPassengerQuery = "INSERT INTO Passenger (username, name, address, gender, passport, nationality, phonenumber, gmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = c.conn.prepareStatement(insertPassengerQuery);
                        pstmt.setString(1, username);
                        pstmt.setString(2, name);
                        pstmt.setString(3, address);
                        pstmt.setString(4, gender);
                        pstmt.setString(5, passport_no);
                        pstmt.setString(6, nation);
                        pstmt.setString(7, ph);
                        pstmt.setString(8, email);
                        pstmt.executeUpdate();
                        pstmt.close();

                        // Insert passenger login credentials into the database
                        String insertLoginQuery = "INSERT INTO PassengerLogin (username, password) VALUES (?, ?)";
                        pstmt = c.conn.prepareStatement(insertLoginQuery);
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.executeUpdate();
                        pstmt.close();

                        JOptionPane.showMessageDialog(null, "Welcome to the Airline Management System. \n You are successfully signed up!");

                        // Fetch passenger data from the database and create Passenger object
                        GetNewPassengerInfo p = new GetNewPassengerInfo().getPassengerDataFromDB(username);
                        // Redirect to Passenger Dashboard
                        new PassengerDashBoard(p);
                        dispose();

                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null, "User Exists");
                        ee.printStackTrace();
                    }
                }
            }
        });

        // Set content pane size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setContentPane(background);
        setLayout(new BorderLayout());
        setSize(dim.width, dim.height);
        setResizable(true);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new NewPassenger();
    }
}