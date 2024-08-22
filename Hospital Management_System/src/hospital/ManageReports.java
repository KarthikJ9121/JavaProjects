package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageReports extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField reportIdField;

    public ManageReports(AdminLogin admin) {
        setTitle("Manage Reports");
        initialize(admin);
        populateTable();
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(860, 523);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 860, 523); // Adjust bounds to cover the entire frame
        add(backgroundLabel);
        backgroundLabel.setLayout(null); // Use absolute positioning

        // Heading label
        JLabel headingLabel = new JLabel("Manage Reports", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30); // Positioning the heading above the table
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24)); // Font size adjusted for heading
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Table for displaying reports
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 760, 400); // Adjust the size and position as needed
        backgroundLabel.add(scrollPane);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 460, 760, 100); // Adjust the size and position as needed
        backgroundLabel.add(inputPanel);

        // Delete report label and input field
        JLabel deleteReportLabel = new JLabel("Enter Appointment ID:");
        deleteReportLabel.setFont(new Font("Bree Serif", Font.BOLD, 16));
        deleteReportLabel.setBounds(50, 10, 200, 30);
        deleteReportLabel.setForeground(Color.WHITE);
        inputPanel.add(deleteReportLabel);

        reportIdField = new JTextField();
        reportIdField.setFont(new Font("Bree Serif", Font.PLAIN, 16));
        reportIdField.setBounds(250, 10, 200, 30);
        inputPanel.add(reportIdField);

        // Edit, Delete and View Details report buttons
        JButton editReportButton = new JButton("View and Edit");
        editReportButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        editReportButton.setBounds(50, 70, 150, 30);
        editReportButton.setBackground(Color.GREEN); // Blue color
        editReportButton.setForeground(Color.WHITE);
        inputPanel.add(editReportButton);

        JButton deleteHistoryButton = new JButton("Deleted History");
        deleteHistoryButton.setFont(new Font("Bree Serif", Font.BOLD, 16));
        deleteHistoryButton.setBounds(230, 70, 200, 30);
        deleteHistoryButton.setBackground(new Color(255, 165, 0)); // Green color
        deleteHistoryButton.setForeground(Color.WHITE);
        inputPanel.add(deleteHistoryButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(230, 580, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        deleteHistoryButton.addActionListener(e -> {
            // Open DeleteHistory class
            new DeleteHistory(admin);
        });

        backButton.addActionListener(e -> {
            new AdminDashboard(admin);
            dispose();
        });

        // Add action listener to the buttons
//        editReportButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                editReport(reportIdField);
//            }
//        });

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    
    
 editReportButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        String appointmentIdText = reportIdField.getText();
        if (!appointmentIdText.isEmpty()) {
            try {
                int appointmentId = Integer.parseInt(appointmentIdText);

                // Fetch appointment details from the database
                DbConn c = new DbConn();
                String fetchQuery = "SELECT * FROM reports WHERE appointment_id = ?";
                PreparedStatement fetchStmt = c.conn.prepareStatement(fetchQuery);
                fetchStmt.setInt(1, appointmentId);
                ResultSet rs = fetchStmt.executeQuery();

                if (rs.next()) {
                    // Display details in a prompt
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Edit Appointment Status");
                    dialog.setBounds(700, 40, 500, 650);
                    dialog.setLayout(new GridBagLayout());

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 0;

                    dialog.add(new JLabel("Report ID:"), gbc);
                    gbc.gridx = 1;
                    JTextField reportIdField = new JTextField(rs.getString("report_id"), 20);
                    reportIdField.setEditable(false);
                    dialog.add(reportIdField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Appointment ID:"), gbc);
                    gbc.gridx = 1;
                    JTextField appointmentIdField = new JTextField(rs.getString("appointment_id"), 20);
                    appointmentIdField.setEditable(false);
                    dialog.add(appointmentIdField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Patient ID:"), gbc);
                    gbc.gridx = 1;
                    JTextField patientIdField = new JTextField(rs.getString("patient_id"), 20);
                    patientIdField.setEditable(false);
                    dialog.add(patientIdField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Patient Name:"), gbc);
                    gbc.gridx = 1;
                    JTextField patientNameField = new JTextField(rs.getString("patient_name"), 20);
                    patientNameField.setEditable(false);
                    dialog.add(patientNameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Age:"), gbc);
                    gbc.gridx = 1;
                    JTextField ageField = new JTextField(rs.getString("age"), 20);
                    ageField.setEditable(false);
                    dialog.add(ageField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Gender:"), gbc);
                    gbc.gridx = 1;
                    JTextField genderField = new JTextField(rs.getString("gender"), 20);
                    genderField.setEditable(false);
                    dialog.add(genderField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Phone No:"), gbc);
                    gbc.gridx = 1;
                    JTextField phoneNoField = new JTextField(rs.getString("phone_no"), 20);
                    phoneNoField.setEditable(false);
                    dialog.add(phoneNoField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Address:"), gbc);
                    gbc.gridx = 1;
                    JTextField addressField = new JTextField(rs.getString("address"), 20);
                    addressField.setEditable(false);
                    dialog.add(addressField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Disease:"), gbc);
                    gbc.gridx = 1;
                    JTextField diseaseField = new JTextField(rs.getString("disease"), 20);
                    diseaseField.setEditable(false);
                    dialog.add(diseaseField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Doctor Name:"), gbc);
                    gbc.gridx = 1;
                    JTextField doctorNameField = new JTextField(rs.getString("doctor_name"), 20);
                    doctorNameField.setEditable(false);
                    dialog.add(doctorNameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Date of Appointment:"), gbc);
                    gbc.gridx = 1;
                    JTextField dateOfAppointmentField = new JTextField(rs.getString("date_of_appointment"), 20);
                    dateOfAppointmentField.setEditable(false);
                    dialog.add(dateOfAppointmentField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Status:"), gbc);
                    gbc.gridx = 1;
                    JTextField statusField = new JTextField(rs.getString("status"), 20);
                    statusField.setEditable(false);
                    dialog.add(statusField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Date Of Report:"), gbc);
                    gbc.gridx = 1;
                    JTextField dateOfReportField = new JTextField(rs.getString("date_of_report"), 20);
                    dialog.add(dateOfReportField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy++;
                    dialog.add(new JLabel("Report Info:"), gbc);
                    gbc.gridx = 1;
                    JTextField reportInfoField = new JTextField(rs.getString("report_info"), 20);
                    dialog.add(reportInfoField, gbc);

                    gbc.gridx = 1;
                    gbc.gridy++;
                    gbc.anchor = GridBagConstraints.CENTER;
                    JButton saveButton = new JButton("Save");
                    saveButton.setBackground(Color.GREEN);
                    saveButton.setForeground(Color.WHITE);
                    dialog.add(saveButton, gbc);

                    saveButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                // Ensure date_of_report is properly formatted and not empty
                                String dateOfReportText = dateOfReportField.getText().trim();
                                if (dateOfReportText.isEmpty()) {
                                    JOptionPane.showMessageDialog(dialog, "Date of Report cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                // Update the patient details in the patients table
                                String updatePatientQuery = "UPDATE patients SET report_id = ?, date_of_report = ?, report_info = ? WHERE patient_id = ?";
                                PreparedStatement updatePatientStmt = c.conn.prepareStatement(updatePatientQuery);
                                updatePatientStmt.setInt(1, rs.getInt("report_id"));
                                updatePatientStmt.setString(2, dateOfReportText);
                                updatePatientStmt.setString(3, reportInfoField.getText());
                                updatePatientStmt.setInt(4, rs.getInt("patient_id"));
                                updatePatientStmt.executeUpdate();

                                // Update the appointment details in the appointments table
                                String updateAppointmentQuery = "UPDATE appointments SET report_id = ?, date_of_report = ?, report_info = ? WHERE appointment_id = ?";
                                PreparedStatement updateAppointmentStmt = c.conn.prepareStatement(updateAppointmentQuery);
                                updateAppointmentStmt.setInt(1, rs.getInt("report_id"));
                                updateAppointmentStmt.setString(2, dateOfReportText);
                                updateAppointmentStmt.setString(3, reportInfoField.getText());
                                updateAppointmentStmt.setInt(4, rs.getInt("appointment_id"));
                                updateAppointmentStmt.executeUpdate();

                                // Update the report details in the reports table
                                String updateReportQuery = "UPDATE reports SET date_of_report = ?, report_info = ? WHERE appointment_id = ?";
                                PreparedStatement updateReportStmt = c.conn.prepareStatement(updateReportQuery);
                                updateReportStmt.setString(1, dateOfReportText);
                                updateReportStmt.setString(2, reportInfoField.getText());
                                updateReportStmt.setInt(3, rs.getInt("appointment_id"));
                                updateReportStmt.executeUpdate();

                                JOptionPane.showMessageDialog(dialog, "Status updated successfully.");
                                populateTable(); // Refresh the table after updating
                                dialog.dispose();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(dialog, "Error: Unable to update status.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No appointment found for the given Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch appointment data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        reportIdField.setText("");
    }
});
    }
    

private void populateTable() {
    tableModel.setRowCount(0); // Clear existing rows
    tableModel.setColumnIdentifiers(new String[]{
        "Report ID", "Appointment ID", "Patient ID", "Patient Name", "Age", "Gender", "Phone No.", "Address", "Disease", "Doctor",
        "Date of Appointment", "Status", "Report Date", "Report Info" // Added "Status" column
    });

    try {
        DbConn dbConn = new DbConn();
        String getReportsQuery = "SELECT * FROM reports";
        Statement stmt = dbConn.conn.createStatement();
        ResultSet rs = stmt.executeQuery(getReportsQuery);

        while (rs.next()) {
            Object[] rowData = {
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
                rs.getString("date_of_appointment"),
                rs.getString("status"),
                rs.getString("date_of_report"),
                rs.getString("report_info")
            };
            tableModel.addRow(rowData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error populating report data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {
//        AdminLogin admin = new AdminLogin("admin"); // Replace with actual AdminLogin object
        new ManageReports(null);
    }
}

           