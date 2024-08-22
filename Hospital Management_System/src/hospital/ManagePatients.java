package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManagePatients extends JFrame {

    private JTable table;
    private JTextField patientIdField;
    private DefaultTableModel tableModel;

    public ManagePatients(AdminLogin admin) {
    	setTitle("Manage Patients");
        initialize(admin);
        refreshTable();
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        add(backgroundLabel);

        // Heading label
        JLabel headingLabel = new JLabel("Manage Patients", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30);
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 470, 760, 50);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 760, 400);
        backgroundLabel.add(scrollPane);

        JLabel idLabel = new JLabel("Enter Patient ID");
        idLabel.setBounds(150, 10, 180, 20);
        idLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        patientIdField = new JTextField();
        patientIdField.setBounds(300, 10, 200, 20);
        patientIdField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(patientIdField);

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.setBounds(540, 10, 150, 30);
        deleteButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 51, 51));
        deleteButton.setForeground(Color.WHITE);
        inputPanel.add(deleteButton);
        
        JButton deleteHistoryButton = new JButton("Delete History");
        deleteHistoryButton.setBounds(260, 550, 150, 30);
        deleteHistoryButton.setBackground(new Color(255, 165, 0)); // Light orange color
        deleteHistoryButton.setForeground(Color.WHITE);
        backgroundLabel.add(deleteHistoryButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(450, 550, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(e -> {
        	new AdminDashboard(admin);
        	dispose();
        });

        // Delete button action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String patientIdtext = patientIdField.getText();
                if (!patientIdtext.isEmpty()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this patient?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            int patientId = Integer.parseInt(patientIdtext);

                            // Fetch patient details from the database
                            DbConn c = new DbConn();
                            String fetchQuery = "SELECT * FROM patients WHERE patient_id = ?";
                            PreparedStatement fetchStmt = c.conn.prepareStatement(fetchQuery);
                            fetchStmt.setInt(1, patientId);
                            ResultSet rs = fetchStmt.executeQuery();

                            if (rs.next()) {
                                // Insert the fetched details into the history table with default values for missing fields
                                String insertQuery = "INSERT INTO history (report_id, appointment_id, patient_id, patient_name, age, gender, phone_no, address, disease, doctor_name, date_of_appointment, status, date_of_report, report_info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                PreparedStatement insertStmt = c.conn.prepareStatement(insertQuery);
                                insertStmt.setInt(1, rs.getInt("report_id")); // Default report_id
                                insertStmt.setInt(2, rs.getInt("appointment_id")); // Default appointment_id
                                insertStmt.setInt(3, rs.getInt("patient_id"));
                                insertStmt.setString(4, rs.getString("patient_name"));
                                insertStmt.setInt(5, rs.getInt("age"));
                                insertStmt.setString(6, rs.getString("gender"));
                                insertStmt.setString(7, "phone_no");
                                insertStmt.setString(8, "address");
                                insertStmt.setString(9, rs.getString("disease"));
                                insertStmt.setString(10, rs.getString("doctor_name")); // Default doctor
                                insertStmt.setString(11, rs.getString("date_of_appointment")); // Default date_of_appointment
                                insertStmt.setString(12,  rs.getString("status"));
                                insertStmt.setDate(13, rs.getDate("date_of_report")); // Current date for report_date
                                insertStmt.setString(14, rs.getString("report_info")); // Default report_info
                                insertStmt.executeUpdate();
                                
                                String deleteQuery4 = "DELETE FROM reports WHERE patient_id = ?";
                                PreparedStatement deleteStmt4 = c.conn.prepareStatement(deleteQuery4);
                                deleteStmt4.setInt(1, patientId);
                                deleteStmt4.executeUpdate();
                                
                                String deleteQuery3 = "DELETE FROM appointments WHERE patient_id = ?";
                                PreparedStatement deleteStmt3 = c.conn.prepareStatement(deleteQuery3);
                                deleteStmt3.setInt(1, patientId);
                                deleteStmt3.executeUpdate();
                                
                                String deleteQuery5 = "DELETE FROM patients WHERE patient_id = ?";
                                PreparedStatement deleteStmt5 = c.conn.prepareStatement(deleteQuery5);
                                deleteStmt5.setInt(1, patientId);
                                deleteStmt5.executeUpdate();
                                
                                String deleteQuery6 = "DELETE FROM patients WHERE patient_id = ?";
                                PreparedStatement deleteStmt6 = c.conn.prepareStatement(deleteQuery6);
                                deleteStmt6.setInt(1, patientId);
                                deleteStmt6.executeUpdate();
                                
                                JOptionPane.showMessageDialog(null, "Patient Data Deleted successfully.");
                                refreshTable(); // Refresh the table after deletion
                                patientIdField.setText(""); // Clear the input field

                            } else {
                                JOptionPane.showMessageDialog(null, "No patient found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error: Unable to delete patient.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                patientIdField.setText("");
            }
           
        });

        // Delete history button action
        deleteHistoryButton.addActionListener(e -> {
            new DeleteHistory(admin);
        });

        backgroundLabel.add(inputPanel);

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Patient ID");
        tableModel.addColumn("Patient Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");

        try {
            // Retrieve patients data from the database
            DbConn c = new DbConn();
            String query = "SELECT * FROM patients";
            ResultSet rs = c.stmt.executeQuery(query);
            while (rs.next()) {
                Object[] rowData = {
                    rs.getString("patient_id"),
                    rs.getString("patient_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("phone_no"),
                    rs.getString("address")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch data from database!");
        }
    }

    public static void main(String[] args) {
        new ManagePatients(null);
    }
}
