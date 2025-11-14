package Reports;

import generateReports.reportgeneration;

public class VehicleUtilizationReport extends report implements reportgeneration{

	public VehicleUtilizationReport() {
		super("Vehicle Utilization Report");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getsql() {
		// TODO Auto-generated method stub
		return "SELECT v.vehicleID, v.licensePlate, "
				+ "ROUND ((SUM(s.weight)/ v.capacity)";
	}

	@Override
	protected String[] getreportHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getFilename() {
		// TODO Auto-generated method stub
		return "VehicleUtilizationReport.pdf";
	}

}
