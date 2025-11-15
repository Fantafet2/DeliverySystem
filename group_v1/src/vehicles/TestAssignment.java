package vehicles;

import java.time.LocalDateTime;

public class TestAssignment {
    public static void main(String[] args) {
        try {
            AssignmentService service = new AssignmentService();

            int vehicleID = 1;
            LocalDateTime start = LocalDateTime.of(2025, 11, 12, 8, 0);
            LocalDateTime end = LocalDateTime.of(2025, 11, 12, 17, 0);

            service.assignVehicleToRoute(vehicleID, start, end, "Kingston → Montego Bay", 10, 1500.0);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
