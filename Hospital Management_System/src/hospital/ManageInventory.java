package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageInventory extends JFrame {

    private JTable table;
    private JTextField equipmentIdField;
    private DefaultTableModel tableModel;

    public ManageInventory(AdminLogin admin) {
        setTitle("Inventory");
        initialize();
        refreshTable();
    }

    private void initialize() {
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
        JLabel headingLabel = new JLabel("Inventory", SwingConstants.CENTER);
        headingLabel.setBounds(50, 10, 760, 30);
        headingLabel.setFont(new Font("Bree Serif", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 470, 760, 100);

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

        JLabel idLabel = new JLabel("Enter Equipment ID");
        idLabel.setBounds(150, 10, 180, 20);
        idLabel.setFont(new Font("Bree Serif", Font.BOLD, 14));
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        equipmentIdField = new JTextField();
        equipmentIdField.setBounds(300, 10, 200, 20);
        equipmentIdField.setFont(new Font("Bree Serif", Font.BOLD, 14));
        inputPanel.add(equipmentIdField);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(150, 60, 80, 30);
        editButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        editButton.setBackground(Color.YELLOW);
        editButton.setForeground(Color.BLACK);
        inputPanel.add(editButton);

        JButton addButton = new JButton("Add");
        addButton.setBounds(250, 60, 80, 30);
        addButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.BLACK);
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(350, 60, 100, 30);
        deleteButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        inputPanel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(480, 60, 80, 30);
        backButton.setFont(new Font("Bree Serif", Font.BOLD, 14));
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        inputPanel.add(backButton);

        backgroundLabel.add(inputPanel);

        // Button actions
        editButton.addActionListener(e -> editInventory());
        addButton.addActionListener(e -> addInventory());
        deleteButton.addActionListener(e -> deleteInventory());
        backButton.addActionListener(e -> dispose());

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear the existing rows
        tableModel.setColumnCount(0); // Clear existing columns
        tableModel.addColumn("Equipment ID");
        tableModel.addColumn("Equipment Name");
        tableModel.addColumn("Specifications");
        tableModel.addColumn("Price");

        try {
            // Retrieve inventory data from the database
            DbConn c = new DbConn();
            String query = "SELECT * FROM inventory";
            ResultSet rs = c.stmt.executeQuery(query);
            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("equipment_id"),
                    rs.getString("equipment_name"),
                    rs.getString("specifications"),
                    rs.getDouble("price")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch data from database!");
        }
    }

    private void editInventory() {
        String id = equipmentIdField.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an equipment ID.");
            return;
        }
        try {
            int equipmentId = Integer.parseInt(id);
            DbConn c = new DbConn();
            String query = "SELECT * FROM inventory WHERE equipment_id = ?";
            PreparedStatement stmt = c.conn.prepareStatement(query);
            stmt.setInt(1, equipmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                JTextField nameField = new JTextField(rs.getString("equipment_name"));
                JTextArea specField = new JTextArea(rs.getString("specifications"));
                specField.setRows(4);
                specField.setColumns(20);
                JScrollPane specScrollPane = new JScrollPane(specField);
                JTextField priceField = new JTextField(rs.getDouble("price") + "");

                Object[] message = {
                    "Equipment Name:", nameField,
                    "Specifications:", specScrollPane,
                    "Price:", priceField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Edit Inventory", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (nameField.getText().isEmpty() || specField.getText().isEmpty() || priceField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid price value.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String updateQuery = "UPDATE inventory SET equipment_name = ?, specifications = ?, price = ? WHERE equipment_id = ?";
                    PreparedStatement updateStmt = c.conn.prepareStatement(updateQuery);
                    updateStmt.setString(1, nameField.getText());
                    updateStmt.setString(2, specField.getText());
                    updateStmt.setDouble(3, Double.parseDouble(priceField.getText()));
                    updateStmt.setInt(4, equipmentId);
                    updateStmt.executeUpdate();
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Equipment updated successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No equipment found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        equipmentIdField.setText("");
    }

    private void addInventory() {
        JTextField nameField = new JTextField();
        JTextArea specField = new JTextArea();
        specField.setRows(4);
        specField.setColumns(20);
        JScrollPane specScrollPane = new JScrollPane(specField);
        JTextField priceField = new JTextField();

        Object[] message = {
            "Equipment Name:", nameField,
            "Specifications:", specScrollPane,
            "Price:", priceField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Inventory", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            if (nameField.getText().isEmpty() || specField.getText().isEmpty() || priceField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DbConn c = new DbConn();
                String query = "INSERT INTO inventory (equipment_name, specifications, price) VALUES (?, ?, ?)";
                PreparedStatement stmt = c.conn.prepareStatement(query);
                stmt.setString(1, nameField.getText());
                stmt.setString(2, specField.getText());
                stmt.setDouble(3, Double.parseDouble(priceField.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Equipment Added Successfully");
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteInventory() {
        String id = equipmentIdField.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an equipment ID.");
            return;
        }
        try {
            int equipmentId = Integer.parseInt(id);
            DbConn c = new DbConn();
            String query = "DELETE FROM inventory WHERE equipment_id = ?";
            PreparedStatement stmt = c.conn.prepareStatement(query);
            stmt.setInt(1, equipmentId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Equipment Deleted Successfully");
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ManageInventory(null);
    }
}
