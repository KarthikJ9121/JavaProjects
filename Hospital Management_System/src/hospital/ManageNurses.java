package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageNurses extends JFrame {
    private JTextField nurseIdField;
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageNurses(AdminLogin admin) {
        setTitle("Manage Nurses");
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
        JLabel headingLabel = new JLabel("Manage Nurses", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30); // Positioning the heading above the table
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24)); // Font size adjusted for heading
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Table panel
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
        inputPanel.setBounds(50, 470, 760, 50); // Adjust the size and position as needed

        JLabel nurseIdLabel = new JLabel("Enter Nurse ID");
        nurseIdLabel.setBounds(150, 10, 150, 20);
        nurseIdLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        nurseIdLabel.setForeground(Color.WHITE);
        inputPanel.add(nurseIdLabel);

        nurseIdField = new JTextField();
        nurseIdField.setBounds(300, 10, 200, 20);
        nurseIdField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(nurseIdField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(510, 10, 100, 30);
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
            new Nurses(admin);
            dispose();
        });

        backgroundLabel.add(inputPanel);

        // Populate the table with nurses' data
        populateTable();

        // Delete button action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String nurseIdText = nurseIdField.getText();
                if (!nurseIdText.isEmpty()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this nurse?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            int nurseId = Integer.parseInt(nurseIdText);
                            DbConn c = new DbConn();
                            String query = "DELETE FROM nurses WHERE id = ?";
                            PreparedStatement pstmt = c.conn.prepareStatement(query);
                            pstmt.setInt(1, nurseId);
                            int rowsAffected = pstmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Nurse deleted successfully!");
                                populateTable(); // Refresh the table after deletion
                                nurseIdField.setText(""); // Clear the input field
                            } else {
                                JOptionPane.showMessageDialog(null, "Nurse not found with the provided ID.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error deleting nurse.", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Nurse ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Nurse ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void populateTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Nurse ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Qualification");

        try {
            DbConn dbConn = new DbConn();
            String query = "SELECT * FROM nurses";
            ResultSet rs = dbConn.stmt.executeQuery(query);
            while (rs.next()) {
                Object[] rowData = { 
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getInt("age"), 
                    rs.getString("gender"), 
                    rs.getString("phone"), 
                    rs.getString("email"), 
                    rs.getString("address"), 
                    rs.getString("qualification")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching nurse information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ManageNurses(null);
    }
}
