package vehicles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VehicleSchedulingGUI extends JFrame {

    private VehicleDAO vehicleDAO = new VehicleDAO();
    private AssignmentService assignmentService = new AssignmentService();

    private JTable vehicleTable;
    private DefaultTableModel vehicleTableModel;

    private JTextField txtStartTime, txtEndTime, txtRoute, txtWeight, txtQuantity;
    private JButton btnAssign, btnRefresh;

    public VehicleSchedulingGUI() {
        setTitle("Vehicle & Scheduling Module");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Layout ---
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("Vehicle Scheduling and Assignment System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Table for vehicle data
        String[] columns = {"ID", "Brand", "Model", "Year", "Weight Cap", "Qty Cap", "Status"};
        vehicleTableModel = new DefaultTableModel(columns, 0);
        vehicleTable = new JTable(vehicleTableModel);
        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        add(scrollPane, BorderLayout.CENTER);

        // Form panel (bottom)
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Assign Vehicle to Route"));

        txtStartTime = new JTextField("2025-11-12T08:00");
        txtEndTime = new JTextField("2025-11-12T17:00");
        txtRoute = new JTextField();
        txtWeight = new JTextField();
        txtQuantity = new JTextField();

        formPanel.add(new JLabel("Start Time (YYYY-MM-DDTHH:MM):"));
        formPanel.add(txtStartTime);
        formPanel.add(new JLabel("End Time (YYYY-MM-DDTHH:MM):"));
        formPanel.add(txtEndTime);

        formPanel.add(new JLabel("Route Description:"));
        formPanel.add(txtRoute);
        formPanel.add(new JLabel("Total Weight (kg):"));
        formPanel.add(txtWeight);

        formPanel.add(new JLabel("Total Packages:"));
        formPanel.add(txtQuantity);

        btnAssign = new JButton("Assign Vehicle");
        btnRefresh = new JButton("Refresh Vehicles");

        formPanel.add(btnAssign);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        // Load data
        refreshVehicleTable();

        // Event Handlers
        btnAssign.addActionListener(e -> assignVehicle());
        btnRefresh.addActionListener(e -> refreshVehicleTable());

        setVisible(true);
    }

    // Refresh the table from DB
    private void refreshVehicleTable() {
        vehicleTableModel.setRowCount(0);
        try {
            List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
            for (Vehicle v : vehicles) {
                vehicleTableModel.addRow(new Object[]{
                        v.getVehicleID(), v.getBrand(), v.getModel(), v.getYear(),
                        v.getWeightCapacity(), v.getQuantityCapacity(), v.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading vehicles: " + e.getMessage());
        }
    }

    // Assign selected vehicle to route
    private void assignVehicle() {
        int row = vehicleTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle first.");
            return;
        }

        try {
            int vehicleID = (int) vehicleTableModel.getValueAt(row, 0);
            double totalWeight = Double.parseDouble(txtWeight.getText().trim());
            int totalPackages = Integer.parseInt(txtQuantity.getText().trim());
            String route = txtRoute.getText().trim();

            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(txtStartTime.getText().trim(), fmt);
            LocalDateTime end = LocalDateTime.parse(txtEndTime.getText().trim(), fmt);

            assignmentService.assignVehicleToRoute(vehicleID, start, end, route, totalPackages, totalWeight);
            JOptionPane.showMessageDialog(this, "Vehicle successfully assigned!");

            refreshVehicleTable();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers for weight and quantity.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Run the GUI
  /* public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicleSchedulingGUI::new);
    }
}*\
