package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CancellationHistory extends JFrame {

    private GetPatientInfo patient;
    private DefaultTableModel model;
    private JTextField appointmentIdField;

    public CancellationHistory(GetPatientInfo patient) {
        super("Cancellation History");
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
        JLabel headingLabel = new JLabel("Cancellation History", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 700, 30);
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Table setup
        String[] columns = {"Report ID", "Appointment ID", "Patient ID", "Patient Name", "Age", "Gender", "Phone Number", "Address", "Disease", "Doctor Name", "Date of Appointment", "Status", "Date of Report", "Report Info"};
        model = new DefaultTableModel(columns, 0);
        JTable historyTable = new JTable(model);
        historyTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        historyTable.setRowHeight(30);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        historyTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBounds(50, 50, 700, 500);
        backgroundLabel.add(scrollPane);

        System.out.println(patient.getPatientId());
        // Fetch history data from database
        try {
            DbConn conn = new DbConn();
            String query = "SELECT report_id, appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status, date_of_report, report_info FROM history WHERE patient_id = ?";
            PreparedStatement pstmt = conn.conn.prepareStatement(query);
            int patientId = (patient.getName() != null) ? patient.getPatientId() : 0;
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            // Populate the table with data
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("report_id"),
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
                        rs.getString("status"),
                        rs.getDate("date_of_report"),
                        rs.getString("report_info")
                };
                model.addRow(row);
            }

            pstmt.close();
            conn.conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }

        // Input panel for view button
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 560, 700, 40);

        JLabel idLabel = new JLabel("Enter Appointment ID:");
        idLabel.setBounds(10, 10, 170, 20);
        idLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        appointmentIdField = new JTextField();
        appointmentIdField.setBounds(190, 10, 120, 20);
        inputPanel.add(appointmentIdField);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(350, 5, 100, 30);
        viewButton.setBackground(new Color(255, 165, 0)); // Orange color
        viewButton.setForeground(Color.WHITE);
        inputPanel.add(viewButton);

        backgroundLabel.add(inputPanel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 565, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new PatientDashBoard(patient);
                dispose();
            }
        });

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
    }

    // Method to view appointment details
    private void viewAppointmentDetails(int appointmentId) {
        try {
            DbConn conn = new DbConn();
            String query = "SELECT * FROM history WHERE appointment_id = ?";
            PreparedStatement pstmt = conn.conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Display details in a dialog prompt
                JPanel panel = new JPanel(new GridLayout(0, 2));
                panel.add(new JLabel("Report Id:"));
                panel.add(new JLabel(String.valueOf(rs.getInt("report_id"))));
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
                panel.add(new JLabel("Date of Report:"));
                JTextField dateOfReportField = new JTextField(rs.getString("date_of_report"));
                panel.add(dateOfReportField);
                panel.add(new JLabel("Report Info:"));
                JTextField reportInfoField = new JTextField(rs.getString("report_info"));
                panel.add(reportInfoField);

                int option = JOptionPane.showConfirmDialog(null, panel, "View Appointment Details", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No appointment found with ID: " + appointmentId, "Error", JOptionPane.ERROR_MESSAGE);
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
        new CancellationHistory(patient);
    }
}
