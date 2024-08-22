package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageDoctors extends JFrame {

    private JTextField doctorIdField;
    private JTable doctorTable;
    private DefaultTableModel tableModel;

    public ManageDoctors(AdminLogin admin) {
        setTitle("Manage Doctors");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(860, 523);
        setLocationRelativeTo(null);
        setResizable(true);

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null); // Use null layout for free positioning
        setContentPane(backgroundLabel);

        // Heading label
        JLabel headingLabel = new JLabel("Manage Doctors", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30); // Positioning the heading above the table
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24)); // Font size adjusted for heading
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Table panel
        tableModel = new DefaultTableModel();
        doctorTable = new JTable(tableModel);
        doctorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        doctorTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setBounds(50, 50, 760, 400); // Adjust the size and position as needed
        backgroundLabel.add(scrollPane);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 470, 760, 50); // Adjust the size and position as needed

        JLabel doctorIdLabel = new JLabel("Enter Doctor ID");
        doctorIdLabel.setBounds(150, 10, 120, 20);
        doctorIdLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        doctorIdLabel.setForeground(Color.WHITE);
        inputPanel.add(doctorIdLabel);

        doctorIdField = new JTextField();
        doctorIdField.setBounds(280, 10, 200, 20);
        doctorIdField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(doctorIdField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(490, 10, 100, 30);
        deleteButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 51, 51));
        deleteButton.setForeground(Color.WHITE);
        inputPanel.add(deleteButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(370, 550, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);
  
        backButton.addActionListener(e -> {
        	new Doctors(admin);
        	dispose();
        });
        
        backgroundLabel.add(inputPanel);

        // Populate the table with doctors' data
        populateTable();

        // Delete button action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String doctorIdText = doctorIdField.getText().trim();
                if (!doctorIdText.isEmpty()) {
                    int doctorId;
                    try {
                        doctorId = Integer.parseInt(doctorIdText);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Doctor ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this doctor?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            DbConn connection = new DbConn();
                            String deleteQuery = "DELETE FROM doctors WHERE id = ?";
                            PreparedStatement pstmt = connection.conn.prepareStatement(deleteQuery);
                            pstmt.setInt(1, doctorId);
                            int rowsAffected = pstmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Doctor deleted successfully!");
                                populateTable(); // Refresh the table after deletion
                                doctorIdField.setText(""); // Clear the input field
                            } else {
                                JOptionPane.showMessageDialog(null, "Doctor not found with the provided ID.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Doctor ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void populateTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Doctor ID");
        tableModel.addColumn("Doctor Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Specialization");

        try {
            DbConn dbConn = new DbConn();
            String query = "SELECT * FROM doctors";
            ResultSet rs = dbConn.stmt.executeQuery(query);
            while (rs.next()) {
                Object[] rowData = { rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("specialization") };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching doctor information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ManageDoctors(null);
    }
}
