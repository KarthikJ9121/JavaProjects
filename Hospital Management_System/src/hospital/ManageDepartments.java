package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageDepartments extends JFrame {

    private JTextField departmentNameField;
    private JTable departmentTable;
    private DefaultTableModel tableModel;

    public ManageDepartments(AdminLogin admin) {
        setTitle("Manage Departments");
        initialize(admin);
        populateTable();
    }

    private void initialize(AdminLogin admin) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(860, 523); // Adjusted size to match the AdminDashboard
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon backgroundImage = new ImageIcon("hospitalImages/hospitalImg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 860, 523); // Adjust bounds to cover the entire frame
        add(backgroundLabel);
        
        // Heading label
        JLabel headingLabel = new JLabel("Manage Departments", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30); // Positioning the heading above the table
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24)); // Font size adjusted for heading
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 470, 760, 50); // Adjust the size and position as needed
        
        tableModel = new DefaultTableModel();
        departmentTable = new JTable(tableModel);
        departmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Center align text in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        departmentTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(departmentTable);
        scrollPane.setBounds(50, 50, 760, 400); // Adjust the size and position as needed
        backgroundLabel.add(scrollPane);

        JLabel nameLabel = new JLabel("Enter Department Name");
        nameLabel.setBounds(150, 10, 180, 20);
        nameLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        inputPanel.add(nameLabel);

        departmentNameField = new JTextField();
        departmentNameField.setBounds(330, 10, 200, 20);
        departmentNameField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(departmentNameField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(540, 10, 100, 30);
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
        	new Departments(admin);
        	dispose();
        });

        backgroundLabel.add(inputPanel);

        // Populate the table with departments' data
        populateTable();

        // Delete button action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String departmentName = departmentNameField.getText();
                if (!departmentName.isEmpty()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this department?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            DbConn c = new DbConn();
                            String query = "DELETE FROM departments WHERE department_name = ?";
                            PreparedStatement pstmt = c.conn.prepareStatement(query);
                            pstmt.setString(1, departmentName);
                            int rowsAffected = pstmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Department deleted successfully!");
                                populateTable(); // Refresh the table after deletion
                                departmentNameField.setText(""); // Clear the input field
                            } else {
                                JOptionPane.showMessageDialog(null, "Department not found with the provided name.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid department name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void populateTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Department Name");
        tableModel.addColumn("Head of Department");
        tableModel.addColumn("Number of Beds");

        try {
            DbConn dbConn = new DbConn();
            String query = "SELECT * FROM departments";
            ResultSet rs = dbConn.stmt.executeQuery(query);
            while (rs.next()) {
                Object[] rowData = { 
                    rs.getString("department_name"), 
                    rs.getString("head_of_department"), 
                    rs.getInt("number_of_beds") 
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching department information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ManageDepartments(null);
    }
}
