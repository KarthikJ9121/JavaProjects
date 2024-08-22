package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageAppointments extends JFrame {

    private JTextField appointmentIdField;
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageAppointments(AdminLogin admin) {
        setTitle("Manage Appointments");
        initialize(admin);
        refreshTable();
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 650); // Adjusted size to match the AdminDashboard
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 650); // Adjust bounds to cover the entire frame
        add(backgroundLabel);

        // Heading label
        JLabel headingLabel = new JLabel("Manage Appointments", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 800, 30); // Positioning the heading above the table
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24)); // Font size adjusted for heading
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 570, 800, 50); // Adjust the size and position as needed

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 800, 500); // Adjust the size and position as needed
        backgroundLabel.add(scrollPane);

        JLabel idLabel = new JLabel("Enter Appointment ID");
        idLabel.setBounds(150, 10, 180, 20);
        idLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        JTextField appointmentIdField = new JTextField();
        appointmentIdField.setBounds(330, 10, 200, 20);
        appointmentIdField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(appointmentIdField);

        JButton viewAndEditButton = new JButton("View And Edit");
        viewAndEditButton.setBounds(540, 10, 150, 30);
        viewAndEditButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        viewAndEditButton.setBackground(Color.white);
        viewAndEditButton.setForeground(Color.black);
        inputPanel.add(viewAndEditButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(700, 10, 100, 30);
        deleteButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 51, 51));
        deleteButton.setForeground(Color.WHITE);
        inputPanel.add(deleteButton);

        JButton deleteHistoryButton = new JButton("Deleted History");
        deleteHistoryButton.setBounds(590, 630, 150, 30);
        deleteHistoryButton.setBackground(new Color(255, 165, 0)); // Light orange color
        deleteHistoryButton.setForeground(Color.WHITE);
        backgroundLabel.add(deleteHistoryButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(750, 630, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(e -> {
        	new AdminDashboard(admin);
        	dispose();
        });

        // View and Edit button action
       viewAndEditButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
	        String appointmentIdText = appointmentIdField.getText();
	        if (!appointmentIdText.isEmpty()) {
	            try {
	                int appointmentId = Integer.parseInt(appointmentIdText);
	
	                // Fetch appointment details from the database
	                DbConn c = new DbConn();
	                String fetchQuery = "SELECT * FROM appointments WHERE appointment_id = ?";
	                PreparedStatement fetchStmt = c.conn.prepareStatement(fetchQuery);
	                fetchStmt.setInt(1, appointmentId);
	                ResultSet rs = fetchStmt.executeQuery();
	
	                if (rs.next()) {
	                    // Display details in a prompt
	                    JDialog dialog = new JDialog();
	                    dialog.setTitle("Edit Appointment Status");
	                    dialog.setBounds(500, 120, 500, 600);
	                    dialog.setLayout(new GridBagLayout());
	
	                    GridBagConstraints gbc = new GridBagConstraints();
	                    gbc.insets = new Insets(10, 10, 10, 10);
	                    gbc.anchor = GridBagConstraints.WEST;
	                    gbc.fill = GridBagConstraints.HORIZONTAL;
	                    gbc.gridx = 0;
	                    gbc.gridy = 0;
	
	                    dialog.add(new JLabel("Appointment ID:"), gbc);
	                    gbc.gridx = 1;
	                    JTextField appointmentIdField = new JTextField(rs.getString("appointment_id"), 20);
	                    appointmentIdField.setEditable(false);
	                    dialog.add(appointmentIdField, gbc);
	
	                    gbc.gridx = 0;
	                    gbc.gridy++;
	                    dialog.add(new JLabel("Patient ID:"), gbc);
	                    gbc.gridx = 1;
	                    JTextField appointmentIdField1 = new JTextField(rs.getString("patient_id"), 20);
	                    appointmentIdField1.setEditable(false);
	                    dialog.add(appointmentIdField1, gbc);
	
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
	                    String[] statuses = {"pending", "accepted", "rejected"};
	                    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
	                    statusComboBox.setSelectedItem(rs.getString("status"));
	                    dialog.add(statusComboBox, gbc);
	
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
						            String newStatus = (String) statusComboBox.getSelectedItem();
						
						            // Update the status in the appointments table
						            String updateAppointmentQuery = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
						            PreparedStatement updateAppointmentStmt = c.conn.prepareStatement(updateAppointmentQuery);
						            updateAppointmentStmt.setString(1, newStatus);
						            updateAppointmentStmt.setInt(2, rs.getInt("appointment_id"));
						            updateAppointmentStmt.executeUpdate();
						
						            if (newStatus.equals("accepted")) {
						                // Update the patient details in the patients table
						                String updatePatientQuery = "UPDATE patients SET appointment_id = ?, disease = ?, doctor_name = ?, date_of_appointment = ?, status = ? WHERE patient_id = ?";
						                PreparedStatement updatePatientStmt = c.conn.prepareStatement(updatePatientQuery);
						                updatePatientStmt.setInt(1, rs.getInt("appointment_id"));
						                updatePatientStmt.setString(2, rs.getString("disease"));
						                updatePatientStmt.setString(3, rs.getString("doctor_name"));
						                updatePatientStmt.setDate(4, rs.getDate("date_of_appointment"));
						                updatePatientStmt.setString(5, newStatus);
						                updatePatientStmt.setInt(6, rs.getInt("patient_id"));
						                updatePatientStmt.executeUpdate();
						
						                String checkReportQuery = "SELECT COUNT(*) FROM reports WHERE appointment_id = ?";
						                PreparedStatement checkReportStmt = c.conn.prepareStatement(checkReportQuery);
						                checkReportStmt.setInt(1, rs.getInt("appointment_id"));
						                ResultSet checkRs = checkReportStmt.executeQuery();

						                if (checkRs.next() && checkRs.getInt(1) == 0) {
						                    String insertReportQuery = "INSERT INTO reports (appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						                    PreparedStatement insertReportStmt = c.conn.prepareStatement(insertReportQuery);
						                    insertReportStmt.setInt(1, rs.getInt("appointment_id"));
						                    insertReportStmt.setInt(2, rs.getInt("patient_id"));
						                    insertReportStmt.setString(3, rs.getString("patient_name"));
						                    insertReportStmt.setInt(4, rs.getInt("age"));
						                    insertReportStmt.setString(5, rs.getString("gender"));
						                    insertReportStmt.setString(6, rs.getString("phone_no"));
						                    insertReportStmt.setString(7, rs.getString("address"));
						                    insertReportStmt.setString(8, rs.getString("disease"));
						                    insertReportStmt.setString(9, rs.getString("doctor_name"));
						                    insertReportStmt.setDate(10, rs.getDate("date_of_appointment"));
						                    insertReportStmt.setString(11, newStatus);
						                    insertReportStmt.executeUpdate();
						                }
						            }
						
						            JOptionPane.showMessageDialog(dialog, "Status updated successfully.");
						            refreshTable(); // Refresh the table after updating
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
	        appointmentIdField.setText("");
	    }
	});

        // Delete button action
       deleteButton.addActionListener(e -> {
    	    String appointmentIdText = appointmentIdField.getText();
    	    if (!appointmentIdText.isEmpty()) {
    	        try {
    	            int appointmentId = Integer.parseInt(appointmentIdText);
    	            DbConn c = new DbConn();

    	            // Fetch appointment details before deletion
    	            String fetchQuery = "SELECT * FROM appointments WHERE appointment_id = ?";
    	            PreparedStatement fetchStmt = c.conn.prepareStatement(fetchQuery);
    	            fetchStmt.setInt(1, appointmentId);
    	            ResultSet rs = fetchStmt.executeQuery();

    	            if (rs.next()) {
    	                // Insert data into history table
    	                String insertHistoryQuery = "INSERT INTO history (report_id, appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status, date_of_report, report_info) " +
    	                                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	                PreparedStatement insertHistoryStmt = c.conn.prepareStatement(insertHistoryQuery);
    	                insertHistoryStmt.setInt(1, rs.getInt("report_id"));
    	                insertHistoryStmt.setInt(2, rs.getInt("appointment_id"));
    	                insertHistoryStmt.setInt(3, rs.getInt("patient_id"));
    	                insertHistoryStmt.setString(4, rs.getString("patient_name"));
    	                insertHistoryStmt.setInt(5, rs.getInt("age"));
    	                insertHistoryStmt.setString(6, rs.getString("gender"));
    	                insertHistoryStmt.setString(7, rs.getString("phone_no"));
    	                insertHistoryStmt.setString(8, rs.getString("address"));
    	                insertHistoryStmt.setString(9, rs.getString("disease"));
    	                insertHistoryStmt.setString(10, rs.getString("doctor_name"));
    	                insertHistoryStmt.setDate(11, rs.getDate("date_of_appointment"));
    	                insertHistoryStmt.setString(12, rs.getString("status"));
    	                insertHistoryStmt.setDate(13, rs.getDate("date_of_report"));
    	                insertHistoryStmt.setString(14, rs.getString("report_info"));
    	                insertHistoryStmt.executeUpdate();

    	                // Delete from reports table
    	                String deleteReportQuery = "DELETE FROM reports WHERE appointment_id = ?";
    	                PreparedStatement deleteReportStmt = c.conn.prepareStatement(deleteReportQuery);
    	                deleteReportStmt.setInt(1, appointmentId);
    	                deleteReportStmt.executeUpdate();

    	                // Delete from appointments table
    	                String deleteQuery = "DELETE FROM appointments WHERE appointment_id = ?";
    	                PreparedStatement deleteStmt = c.conn.prepareStatement(deleteQuery);
    	                deleteStmt.setInt(1, appointmentId);
    	                int rowsDeleted = deleteStmt.executeUpdate();

    	               
	                    String updatePatientQuery = "UPDATE patients SET report_id = NULL, appointment_id = NULL, disease = NULL, doctor_name = NULL, status = NULL, date_of_appointment = NULL, date_of_report = NULL, report_info = NULL WHERE patient_id = ?";
	                    PreparedStatement updatePatientStmt = c.conn.prepareStatement(updatePatientQuery);
	                    updatePatientStmt.setInt(1, rs.getInt("patient_id"));
	                    updatePatientStmt.executeUpdate();   	                

    	                if (rowsDeleted > 0) {
    	                    JOptionPane.showMessageDialog(null, "Appointment deleted successfully.");
    	                    refreshTable(); // Refresh the table after deletion
    	                } else {
    	                    JOptionPane.showMessageDialog(null, "No appointment found for the given Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
    	                }
    	            } else {
    	                JOptionPane.showMessageDialog(null, "No appointment found for the given Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
    	            }
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "Error: Unable to delete appointment.", "Error", JOptionPane.ERROR_MESSAGE);
    	        }
    	    } else {
    	        JOptionPane.showMessageDialog(null, "Please enter a valid Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
    	    }
    	    appointmentIdField.setText("");
    	});


        deleteHistoryButton.addActionListener(e -> {
            new DeleteHistory(admin);
        });

        backgroundLabel.add(inputPanel);
    }

    private void refreshTable() {
        DbConn conn = new DbConn();
        try {
            String selectQuery = "SELECT * FROM appointments";
            ResultSet rs = conn.stmt.executeQuery(selectQuery);

            tableModel.setRowCount(0); // Clear existing data
            tableModel.setColumnIdentifiers(new Object[]{"Appointment ID", "Patient ID", "Patient Name", "Age", "Gender", "Phone No", "Address", "Disease", "Doctor Name", "Date of Appointment", "Status"});

            while (rs.next()) {
                Object[] row = new Object[11];
                row[0] = rs.getInt("appointment_id");
                row[1] = rs.getInt("patient_id");
                row[2] = rs.getString("patient_name");
                row[3] = rs.getInt("age");
                row[4] = rs.getString("gender");
                row[5] = rs.getString("phone_no");
                row[6] = rs.getString("address");
                row[7] = rs.getString("disease");
                row[8] = rs.getString("doctor_name");
                row[9] = rs.getString("date_of_appointment");
                row[10] = rs.getString("status");

                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to load appointment data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageAppointments(new AdminLogin()).setVisible(true));
    }
}
