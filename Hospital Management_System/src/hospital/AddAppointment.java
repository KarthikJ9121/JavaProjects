package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddAppointment extends JFrame {
    private JTextField patientIdField, patientNameField, ageField, genderField, phoneField, addressField,
            diseaseField, doctorNameField;
    private JFormattedTextField appointmentDateField;
    private JButton addButton, backButton;
    private Font boldFont;
    private GetPatientInfo patient;
    private JTable doctorsTable;
    private DefaultTableModel tableModel;

    public AddAppointment(GetPatientInfo patient) {
        this.patient = patient;

        setTitle("Add Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initialize();
        setVisible(true);
    }

    private void initialize() {
        boldFont = new Font("Bree Serif", Font.BOLD, 17);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);
        
        JLabel title = new JLabel("Doctors");
        title.setFont(new Font("Bree Serif", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setBounds(350, 15, 300, 30);
        backgroundLabel.add(title);
        
        // Create JTable for doctors data
        String[] columnNames = {"ID", "Name", "Gender", "Specialization"};
        tableModel = new DefaultTableModel(columnNames, 0);
        doctorsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(doctorsTable);
        scrollPane.setBounds(160, 60, 500, 200);
        backgroundLabel.add(scrollPane);
        
        // Fetch and display doctors data
        fetchDoctorsData();

        // Generate labels and fields for appointment details
        JLabel titleLabel = new JLabel("Add Appointment");
        titleLabel.setFont(new Font("Bree Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(260, 300, 300, 30);
        backgroundLabel.add(titleLabel);

        JLabel patientIdLabel = new JLabel("Patient ID");
        patientIdLabel.setFont(boldFont);
        patientIdLabel.setForeground(Color.white);
        patientIdLabel.setBounds(200, 370, 150, 27);
        backgroundLabel.add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setText(String.valueOf(patient.getPatientId())); // Auto-filled from patient parameter
        patientIdField.setEditable(false);
        patientIdField.setBounds(350, 370, 200, 27);
        patientIdField.setFont(boldFont);
        backgroundLabel.add(patientIdField);

        JLabel patientNameLabel = new JLabel("Patient Name");
        patientNameLabel.setFont(boldFont);
        patientNameLabel.setForeground(Color.white);
        patientNameLabel.setBounds(200, 410, 150, 27);
        backgroundLabel.add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setText((patient.getName() != null) ? patient.getName() : "null"); // Auto-filled from patient parameter
        patientNameField.setEditable(false);
        patientNameField.setBounds(350, 410, 200, 27);
        patientNameField.setFont(boldFont);
        backgroundLabel.add(patientNameField);

        // Add more labels and fields for other appointment details (age, gender, phone no, address, etc.)

        JLabel diseaseLabel = new JLabel("Disease");
        diseaseLabel.setFont(boldFont);
        diseaseLabel.setForeground(Color.white);
        diseaseLabel.setBounds(200, 450, 150, 27);
        backgroundLabel.add(diseaseLabel);

        diseaseField = new JTextField();
        diseaseField.setBounds(350, 450, 200, 27);
        diseaseField.setFont(boldFont);
        backgroundLabel.add(diseaseField);

        JLabel doctorNameLabel = new JLabel("Doctor Name");
        doctorNameLabel.setFont(boldFont);
        doctorNameLabel.setForeground(Color.white);
        doctorNameLabel.setBounds(200, 490, 150, 27);
        backgroundLabel.add(doctorNameLabel);

        doctorNameField = new JTextField();
        doctorNameField.setBounds(350, 490, 200, 27);
        doctorNameField.setFont(boldFont);
        backgroundLabel.add(doctorNameField);

        JLabel appointmentDateLabel = new JLabel("Appointment Date");
        appointmentDateLabel.setFont(boldFont);
        appointmentDateLabel.setForeground(Color.white);
        appointmentDateLabel.setBounds(200, 530, 150, 27);
        backgroundLabel.add(appointmentDateLabel);

        // Define date format and add date formatter
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        appointmentDateField = new JFormattedTextField(dateFormat);
        appointmentDateField.setBounds(350, 530, 200, 27);
        appointmentDateField.setFont(boldFont);
        backgroundLabel.add(appointmentDateField);

        // Generate buttons
        addButton = new JButton("Add");
        addButton.setBounds(240, 590, 100, 30);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        backgroundLabel.add(addButton);
        
        JButton ViewAppointments = new JButton("View");
        ViewAppointments.setBounds(360, 590, 100, 30);
        ViewAppointments.setBackground(Color.orange);
        ViewAppointments.setForeground(Color.WHITE);
        ViewAppointments.setFont(boldFont);
        backgroundLabel.add(ViewAppointments);

        backButton = new JButton("Back");
        backButton.setBounds(480, 590, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(boldFont);
        backgroundLabel.add(backButton);

        // Add action listeners
        addButton.addActionListener(e -> addAppointment());
        backButton.addActionListener(e -> {
            new Appointments(patient);
            dispose();
        });
        
        ViewAppointments.addActionListener(e -> {
        	new Appointments(patient);
        	dispose();
        });
    }

    private void fetchDoctorsData() {
        try {
            DbConn dbConn = new DbConn();
            Connection conn = dbConn.conn;

            String query = "SELECT id, name, gender, specialization FROM doctors where gender = '" + patient.getGender() + "' ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String specialization = rs.getString("specialization");

                tableModel.addRow(new Object[]{id, name, gender, specialization});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch doctors data!");
        }
    }

    private void addAppointment() {
        // Retrieve input values
        int patientId = patient.getPatientId();
        String patientName = patient.getName();
        String age = patient.getAge(); // You can implement age calculation based on birthdate
        String gender = patient.getGender(); // Fetch from patient or allow selection
        String phone = patient.getPhoneNo(); // Fetch from patient or allow input
        String address = patient.getAddress(); // Fetch from patient or allow input
        String disease = diseaseField.getText().trim();
        String doctorName = doctorNameField.getText().trim();
        String appointmentDate = appointmentDateField.getText().trim();

        // Validate input fields
        if (disease.isEmpty() || doctorName.isEmpty() || appointmentDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        // Validate date format
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFormat.parse(appointmentDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.");
            return;
        }

        // Insert appointment into database
        try {
            DbConn dbConn = new DbConn();
            Connection conn = dbConn.conn;

            // Prepare SQL statement
            String query = "INSERT INTO appointments (patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientId);
            pstmt.setString(2, patientName);
            pstmt.setString(3, age);
            pstmt.setString(4, gender);
            pstmt.setString(5, phone);
            pstmt.setString(6, address);
            pstmt.setString(7, disease);
            pstmt.setString(8, doctorName);

            // Convert appointment date to SQL Date format
            java.sql.Date sqlDate = java.sql.Date.valueOf(appointmentDate);
            pstmt.setDate(9, sqlDate);

            // Execute update
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Appointment added successfully!");

            // Clear fields after successful addition
            diseaseField.setText("");
            doctorNameField.setText("");
            appointmentDateField.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to add appointment!");
        }
    }

    public static void main(String[] args) {
        // Example usage
        GetPatientInfo patient = new GetPatientInfo(); // Instantiate with actual patient data
        new AddAppointment(patient);
    }
}
