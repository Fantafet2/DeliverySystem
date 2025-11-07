package payment;

import javax.swing.SwingUtilities;

public class Invoice {
	
	public String senderName;
	public String receiverName;
	public String address;
	public String deliveryZone;
	public String pickupZone; 
	public String packageWeight;
	public String packageDemesions;
	public String type;
	public int cost;
	
	public Invoice(String senderName, String receiverName, String address, String deliveryZone, String pickupZone,
			String packageWeight, String packageDemesions, String type) {
		super();
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.address = address;
		this.deliveryZone = deliveryZone;
		this.pickupZone = pickupZone;
		this.packageWeight = packageWeight;
		this.packageDemesions = packageDemesions;
		this.type = type;
		
		try {
			int zone = Integer.parseInt(deliveryZone);
			int weight = Integer.parseInt(packageWeight);
			this.cost = CalculateCost.calculateCost(weight, type, zone);
		}
		catch (NumberFormatException e) {
			
            System.err.println("Error calculating cost: Invalid number format for zone or weight.");
            this.cost = 0; 
        }
		
	}
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryZone() {
		return deliveryZone;
	}

	public void setDeliveryZone(String deliveryZone) {
		this.deliveryZone = deliveryZone;
	}

	public String getPickupZone() {
		return pickupZone;
	}

	public void setPickupZone(String pickupZone) {
		this.pickupZone = pickupZone;
	}

	public String getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(String packageWeight) {
		this.packageWeight = packageWeight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public void createInvoice() {

		SwingUtilities.invokeLater(() -> {
		    generateInvoice g = new generateInvoice(
		        senderName, receiverName, address, deliveryZone,
		        pickupZone, packageWeight, packageDemesions, type
		    );
		    g.showInvoiceDialog();
		});

	}
	

}
