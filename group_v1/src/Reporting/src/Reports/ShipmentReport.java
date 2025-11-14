package Reports;

import generateReports.reportgeneration;

public class ShipmentReport extends report implements reportgeneration{

	public ShipmentReport() {
		super("SHIPMENT REPORT (DAILY/WEEKLY/MONTHLY");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getsql() {
		// TODO Auto-generated method stub
		return "SELECT s.shipmentId, u.fullname AS CustomerName, s.ShipmentType, s.weight,"
				+ "s.zone, s.totalCost, s.expectedDelivery, s.actualDelivery"
				+ "FROM Shipments s"
				+ "JOIN customer c ON s.customer_id = c.customer_id"
				+ "Join Customers c ON s.CustomerID = c.CustomerID"
				+ "ORDER BY s.ShipmentID ASC ";
	}

	@Override
	protected String[] getreportHeader() {
		// TODO Auto-generated method stub
		return new String[] {"Shipment ID, Delivery Addresss, Customer Name, Shipment Type, Zone, weight,expected_delivery, actual_delivery, Total Cost "};
	}

	@Override
	protected String getFilename() {
		// TODO Auto-generated method stub
		return "SHIPMENTREPORT.pdf";
	}

}
