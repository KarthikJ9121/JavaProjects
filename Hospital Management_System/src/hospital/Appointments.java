package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Appointments extends JFrame {

    private GetPatientInfo patient;
    private DefaultTableModel model;
    private JTextField appointmentIdField;

    public Appointments(GetPatientInfo patient) {
        super("Appointments");
        this.patient = patient;
        initialize();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Ensure frame starts maximized
        setVisible(true);
    }

    private void initialize() {
        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        add(backgroundLabel);

        // Heading label
        JLabel headingLabel = new JLabel("Appointments", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 700, 30);
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Table setup
        String[] columns = {"Appointment ID", "Patient ID", "Patient Name", "Age", "Gender", "Phone Number", "Address", "Disease", "Doctor Name", "Date of Appointment", "Status"};
        model = new DefaultTableModel(columns, 0);
        JTable appointmentsTable = new JTable(model);
        appointmentsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        appointmentsTable.setRowHeight(30);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        appointmentsTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(appointmentsTable);
        scrollPane.setBounds(50, 50, 700, 500);
        backgroundLabel.add(scrollPane);

//        System.out.println(patient.getPatientId());
        // Fetch appointments data from database
        try {
        	DbConn conn = new DbConn();
        	String query = "SELECT appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status FROM appointments WHERE patient_id = ?";
        	PreparedStatement pstmt = conn.conn.prepareStatement(query);
        	int patientId = (patient.getName() != null) ? patient.getPatientId() : 0;
        	pstmt.setInt(1, patientId);
        	ResultSet rs = pstmt.executeQuery();

            // Populate the table with data
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getString("patient_name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone_no"),
                        rs.getString("address"),
                        rs.getString("disease"),
                        rs.getString("doctor_name"),
                        rs.getDate("date_of_appointment"),
                        rs.getString("status")
                };
                model.addRow(row);
            }

            pstmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }

        // Input panel for view, add, cancel buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 560, 700, 40);

        JLabel idLabel = new JLabel("Appointment ID:");
        idLabel.setBounds(10, 10, 120, 20);
        idLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        appointmentIdField = new JTextField();
        appointmentIdField.setBounds(150, 10, 120, 20);
        inputPanel.add(appointmentIdField);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(280, 5, 100, 30);
        viewButton.setBackground(new Color(255, 165, 0)); // Orange color
        viewButton.setForeground(Color.WHITE);
        inputPanel.add(viewButton);

        JButton addButton = new JButton("Add");
        addButton.setBounds(390, 5, 100, 30);
        addButton.setBackground(new Color(0, 128, 0)); // Green color
        addButton.setForeground(Color.WHITE);
        inputPanel.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(500, 5, 100, 30);
        cancelButton.setBackground(new Color(255, 51, 51)); // Red color
        cancelButton.setForeground(Color.WHITE);
        inputPanel.add(cancelButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(610, 5, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        inputPanel.add(backButton);
        
        backButton.addActionListener(e -> {
        	new PatientDashBoard(patient);
        	dispose();
        });

        backgroundLabel.add(inputPanel);

        // View button action
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentIdText = appointmentIdField.getText();
                if (!appointmentIdText.isEmpty()) {
                    int appointmentId = Integer.parseInt(appointmentIdText);
                    viewAppointmentDetails(appointmentId);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter an Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add appointment functionality
//                new AddAppointmentDialog(Appointments.this);
            	new AddAppointment(patient);
            	dispose();
            }
        });

        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentIdText = appointmentIdField.getText();
                if (!appointmentIdText.isEmpty()) {
                    int appointmentId = Integer.parseInt(appointmentIdText);
                    cancelAppointment(appointmentId);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter an Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to view appointment details
    private void viewAppointmentDetails(int appointmentId) {
        try {
            DbConn conn = new DbConn();
            String query = "SELECT * FROM appointments WHERE appointment_id = ?";
            PreparedStatement pstmt = conn.conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Display details in a dialog prompt
                JPanel panel = new JPanel(new GridLayout(0, 2));
                panel.add(new JLabel("Appointment ID:"));
                panel.add(new JLabel(String.valueOf(rs.getInt("appointment_id"))));
                panel.add(new JLabel("Patient ID:"));
                panel.add(new JLabel(String.valueOf(rs.getInt("patient_id"))));
                panel.add(new JLabel("Patient Name:"));
                JTextField patientNameField = new JTextField(rs.getString("patient_name"));
                panel.add(patientNameField);
                panel.add(new JLabel("Age:"));
                JTextField ageField = new JTextField(String.valueOf(rs.getInt("age")));
                panel.add(ageField);
                panel.add(new JLabel("Gender:"));
                JTextField genderField = new JTextField(rs.getString("gender"));
                panel.add(genderField);
                panel.add(new JLabel("Phone Number:"));
                JTextField phoneField = new JTextField(rs.getString("phone_no"));
                panel.add(phoneField);
                panel.add(new JLabel("Address:"));
                JTextField addressField = new JTextField(rs.getString("address"));
                panel.add(addressField);
                panel.add(new JLabel("Disease:"));
                JTextField diseaseField = new JTextField(rs.getString("disease"));
                panel.add(diseaseField);
                panel.add(new JLabel("Doctor Name:"));
                JTextField doctorField = new JTextField(rs.getString("doctor_name"));
                panel.add(doctorField);
                panel.add(new JLabel("Date of Appointment:"));
                JTextField dateField = new JTextField(String.valueOf(rs.getDate("date_of_appointment")));
                panel.add(dateField);
                panel.add(new JLabel("Status:"));
                JTextField statusField = new JTextField(rs.getString("status"));
                panel.add(statusField);

                int option = JOptionPane.showConfirmDialog(null, panel, "View Appointment Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    // Save changes to database
                    saveAppointmentChanges(appointmentId, patientNameField.getText(), Integer.parseInt(ageField.getText()), genderField.getText(),
                            phoneField.getText(), addressField.getText(), diseaseField.getText(), doctorField.getText(), Date.valueOf(dateField.getText()), statusField.getText());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No appointment found with ID: " + appointmentId, "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to save appointment changes
    private void saveAppointmentChanges(int appointmentId, String patientName, int age, String gender, String phoneNo, String address,
                                        String disease, String doctorName, Date dateOfAppointment, String status){
            if (status.equalsIgnoreCase("Accepted")) {
                insertIntoReports(appointmentId, patient.getPatientId(), patientName, age, gender, phoneNo, address, disease, doctorName, dateOfAppointment, status);
            }

              
    }

    // Method to insert into reports table
    private void insertIntoReports(int appointmentId, int patientId, String patientName, int age, String gender, String phoneNo, String address,
                                   String disease, String doctorName, Date dateOfAppointment, String status) {
        try {
            DbConn conn = new DbConn();
            String insertQuery = "INSERT INTO reports (appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.conn.prepareStatement(insertQuery);
            pstmt.setInt(1, appointmentId);
            pstmt.setInt(2, patientId);
            pstmt.setString(3, patientName);
            pstmt.setInt(4, age);
            pstmt.setString(5, gender);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, address);
            pstmt.setString(8, disease);
            pstmt.setString(9, doctorName);
            pstmt.setDate(10, dateOfAppointment);
            pstmt.executeUpdate();

            pstmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to cancel appointment
    private void cancelAppointment(int appointmentId) {
        try {
            DbConn conn = new DbConn();
            String fetchQuery = "SELECT * FROM appointments WHERE appointment_id = ?";
            PreparedStatement fetchStmt = conn.conn.prepareStatement(fetchQuery);
            fetchStmt.setInt(1, appointmentId);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                String insertQuery = "INSERT INTO history (report_id, appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status, date_of_report, report_info) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.conn.prepareStatement(insertQuery);
                insertStmt.setInt(1, rs.getInt("report_info"));
                insertStmt.setInt(2, rs.getInt("appointment_id"));
                insertStmt.setInt(3, rs.getInt("patient_id"));
                insertStmt.setString(4, rs.getString("patient_name"));
                insertStmt.setInt(5, rs.getInt("age"));
                insertStmt.setString(6, rs.getString("gender"));
                insertStmt.setString(7, rs.getString("phone_no"));
                insertStmt.setString(8, rs.getString("address"));
                insertStmt.setString(9, rs.getString("disease"));
                insertStmt.setString(10, rs.getString("doctor_name"));
                insertStmt.setDate(11, rs.getDate("date_of_appointment"));
                insertStmt.setString(12, (rs.getString("status") != "pending") ? rs.getString("status") : "cancelled");
                insertStmt.setDate(13, new java.sql.Date(System.currentTimeMillis())); // Current date for report_date
                insertStmt.setString(14, (rs.getString("report_info") != "pending") ? rs.getString("report_info") : "Cancelled appointment.");
                insertStmt.executeUpdate();

                String deleteQuery = "DELETE FROM appointments WHERE appointment_id = ?";
                PreparedStatement deleteStmt = conn.conn.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, appointmentId);
                deleteStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Appointment ID " + appointmentId + " cancelled successfully.");
                refreshAppointmentsTable();
            } else {
                JOptionPane.showMessageDialog(null, "No appointment found with ID: " + appointmentId, "Error", JOptionPane.ERROR_MESSAGE);
            }

            fetchStmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to refresh appointments table
    private void refreshAppointmentsTable() {
        model.setRowCount(0); // Clear existing rows
        try {
            DbConn conn = new DbConn();
            String query = "SELECT appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status FROM appointments WHERE patient_id = ?";
            PreparedStatement pstmt = conn.conn.prepareStatement(query);
            pstmt.setInt(1, patient.getPatientId());
            ResultSet rs = pstmt.executeQuery();

            // Populate the table with data
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getString("patient_name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone_no"),
                        rs.getString("address"),
                        rs.getString("disease"),
                        rs.getString("doctor_name"),
                        rs.getDate("date_of_appointment"),
                        rs.getString("status")
                };
                model.addRow(row);
            }

            pstmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        GetPatientInfo patient = new GetPatientInfo();
        patient.setPatientId(1); // Set patient ID for testing
        new Appointments(patient);
    }
}
