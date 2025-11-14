package Reports;

import java.util.ArrayList;
import java.util.List;

import generateReports.reportgeneration;

public class GenerateReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<reportgeneration> Reports = new ArrayList<>();
		Reports.add(new ShipmentReport()); 
		Reports.add(new RevenueReport()); 
		Reports.add(new DeliveryPerformanceReport()); 
		Reports.add(new VehicleUtilizationReport()); 
		
		for(reportgeneration r : Reports) {
			r.GenerateReport();
		}
		
		System.out.printf("Reports Generated Successfully"); 

	}

}
