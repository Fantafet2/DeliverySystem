package vehicles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
	private final String url = "jdbc:mysql://localhost:3307/shippingdb";
    private final String user = "root";
    private final String pass = "usbw";

    // Add new vehicle
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (Brand, Model, Year, WeightCapacity, QuantityCapacity, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicle.getBrand());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getYear());
            ps.setDouble(4, vehicle.getWeightCapacity());
            ps.setInt(5, vehicle.getQuantityCapacity());
            ps.setString(6, vehicle.getStatus());
            ps.executeUpdate();
        }
    }

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("vehicleID"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getInt("Year"),
                        rs.getDouble("WeightCapacity"),
                        rs.getInt("QuantityCapacity"),
                        rs.getString("Status")
                ));
            }
        }
        return vehicles;
    }

    // Get single vehicle
    public Vehicle getVehicleById(int vehicleID) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicleID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(
                            rs.getInt("vehicleID"),
                            rs.getString("Brand"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getDouble("WeightCapacity"),
                            rs.getInt("QuantityCapacity"),
                            rs.getString("Status")
                    );
                }
            }
        }
        return null;
    }

    // Update vehicle status (e.g. “Available”, “In Use”, “Maintenance”)
    public void updateVehicleStatus(int vehicleID, String status) throws SQLException {
        String sql = "UPDATE vehicles SET Status = ? WHERE vehicleID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, vehicleID);
            ps.executeUpdate();
        }
    }

}
