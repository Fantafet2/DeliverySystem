package driver;

public class DriverFunctions {
	
	public void viewAssignedDeliveries(String driverId) {
	    System.out.println("Showing deliveries for Driver ID: " + driverId);
	}

	public void updatePackageStatus(int shipmentId, String newStatus) {
	    System.out.println("Shipment #" + shipmentId + " status updated to " + newStatus);
	}


}
