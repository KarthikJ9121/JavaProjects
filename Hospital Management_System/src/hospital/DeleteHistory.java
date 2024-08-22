package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteHistory extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField appointmentIdField;

    public DeleteHistory(AdminLogin admin) {
        setTitle("Delete History");
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
        JLabel headingLabel = new JLabel("Delete History", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30);
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

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

        // Input panel for view button
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 460, 760, 40);

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
        
        JButton deleteHistoryButton = new JButton("Delete History");
        deleteHistoryButton.setBounds(520, 465, 130, 30);
        deleteHistoryButton.setBackground(Color.red);
        deleteHistoryButton.setForeground(Color.WHITE);
        backgroundLabel.add(deleteHistoryButton);
        
        deleteHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all history?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // Establish a connection to the database
                        DbConn dbConn = new DbConn();
                        Connection conn = dbConn.conn;

                        // Create a statement
                        Statement stmt = conn.createStatement();

                        // Execute the TRUNCATE TABLE statement
                        String truncateQuery = "TRUNCATE TABLE history";
                        stmt.executeUpdate(truncateQuery);

                        // Close the statement
                        stmt.close();
                        conn.close();

                        JOptionPane.showMessageDialog(null, "History has been deleted successfully.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: Unable to delete history.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    refreshTable();
                }
            }
        });


        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(670, 465, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
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

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Report ID");
        tableModel.addColumn("Appointment ID");
        tableModel.addColumn("Patient ID");
        tableModel.addColumn("Patient Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Disease");
        tableModel.addColumn("Doctor");
        tableModel.addColumn("Date of Appointment");
        tableModel.addColumn("Status");
        tableModel.addColumn("Report Date");
        tableModel.addColumn("Report Info");

        try {
            // Retrieve history data from the database
            DbConn c = new DbConn();
            String query = "SELECT * FROM history"; // Assuming the history table is named reports
            ResultSet rs = c.stmt.executeQuery(query);
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
                        rs.getDate("date_of_report"),
                        rs.getString("report_info")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch data from database!");
        }
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
        new DeleteHistory(null);
    }
}
