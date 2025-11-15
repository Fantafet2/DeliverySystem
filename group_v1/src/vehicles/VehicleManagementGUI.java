package vehicles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class VehicleManagementGUI extends JFrame {

    private VehicleDAO vehicleDAO = new VehicleDAO();
    private DefaultTableModel model;
    private JTable table;

    private JTextField txtBrand, txtModel, txtYear, txtWeight, txtQuantity, txtStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    public VehicleManagementGUI() {
        setTitle("Vehicle Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Fleet Vehicle Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Vehicle table
        String[] cols = {"ID", "Brand", "Model", "Year", "Weight Cap", "Qty Cap", "Status"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Input panel
        JPanel panel = new JPanel(new GridLayout(3, 6, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Vehicle Details"));

        txtBrand = new JTextField();
        txtModel = new JTextField();
        txtYear = new JTextField();
        txtWeight = new JTextField();
        txtQuantity = new JTextField();
        txtStatus = new JTextField();

        panel.add(new JLabel("Brand:"));
        panel.add(txtBrand);
        panel.add(new JLabel("Model:"));
        panel.add(txtModel);
        panel.add(new JLabel("Year:"));
        panel.add(txtYear);

        panel.add(new JLabel("Weight Capacity:"));
        panel.add(txtWeight);
        panel.add(new JLabel("Quantity Capacity:"));
        panel.add(txtQuantity);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatus);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        add(panel, BorderLayout.SOUTH);

        // Event handlers
        btnAdd.addActionListener(e -> addVehicle());
        btnUpdate.addActionListener(e -> updateVehicle());
        btnDelete.addActionListener(e -> deleteVehicle());
        btnRefresh.addActionListener(e -> loadVehicles());

        loadVehicles();
        setVisible(true);
    }

    private void loadVehicles() {
        model.setRowCount(0);
        try {
            List<Vehicle> list = vehicleDAO.getAllVehicles();
            for (Vehicle v : list) {
                model.addRow(new Object[]{
                        v.getVehicleID(), v.getBrand(), v.getModel(),
                        v.getYear(), v.getWeightCapacity(),
                        v.getQuantityCapacity(), v.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void addVehicle() {
        try {
            String brand = txtBrand.getText().trim();
            String modelTxt = txtModel.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());
            double weight = Double.parseDouble(txtWeight.getText().trim());
            int qty = Integer.parseInt(txtQuantity.getText().trim());
            String status = txtStatus.getText().trim();

            Vehicle v = new Vehicle(brand, modelTxt, year, weight, qty, status);
            vehicleDAO.addVehicle(v);
            loadVehicles();
            JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding vehicle: " + e.getMessage());
        }
    }

    private void updateVehicle() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a vehicle to update.");
            return;
        }
        try {
            int id = (int) model.getValueAt(row, 0);
            String status = JOptionPane.showInputDialog(this, "Enter new status (Available/In Use/Maintenance):");
            if (status != null && !status.isEmpty()) {
                vehicleDAO.updateVehicleStatus(id, status);
                loadVehicles();
                JOptionPane.showMessageDialog(this, "Vehicle updated!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating vehicle: " + e.getMessage());
        }
    }

    private void deleteVehicle() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a vehicle to delete.");
            return;
        }
        try {
            int id = (int) model.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this vehicle?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM vehicles WHERE vehicleID = " + id;
                java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3307/shippingdb", "root", "usbw");
                conn.createStatement().executeUpdate(sql);
                conn.close();
                loadVehicles();
                JOptionPane.showMessageDialog(this, "Vehicle deleted!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting vehicle: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicleManagementGUI::new);
    }
}
