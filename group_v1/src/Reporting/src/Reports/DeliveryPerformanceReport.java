package Reports;

import generateReports.reportgeneration;

public class DeliveryPerformanceReport extends report implements reportgeneration {

	public DeliveryPerformanceReport() {
		super("Delivery Performance Report");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getsql() {
		// TODO Auto-generated method stub
		return "SELECT SUM(CASE WHEN actualDelivery <= expected_delivery THEN 1 ELSE 0 END)AS ONTIME "
				+ "SUM(CASE WHEN actualDelivery > expectedDelivery THEN 1 ELSE 0 END)AS DELAYED"
				+ "COUNT(*) AS Total"
				+ "FROM Shipments";
	}

	@Override
	protected String[] getreportHeader() {
		// TODO Auto-generated method stub
		return new String[] {"ON-TIME DELIVERY, DELAYED DELIVERY, TOTAL SHIPMENTS"};
	}

	@Override
	protected String getFilename() {
		// TODO Auto-generated method stub
		return "DeliveryPerformanceReport.pdf";
	}

}
