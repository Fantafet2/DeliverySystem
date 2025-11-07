package clerk;

public class ClerkFunctions {
	
	
	
	public void processShipmentRequest(String senderName, String receiverName, String address, double weight) {
	    System.out.println("Shipment request processed for " + receiverName);
	}
	
	public void assignPackageToRoute(int shipmentId, String routeId) {
	    System.out.println("Shipment #" + shipmentId + " assigned to route " + routeId);
	}

	public void handlePayment(int shipmentId, double amount, String paymentMethod) {
	    System.out.println("Payment of $" + amount + " received for shipment #" + shipmentId);
	}
	
    public double calculateShippingCost(double weight) {
        return weight * 100; // e.g. $100 per kg
    }

}
