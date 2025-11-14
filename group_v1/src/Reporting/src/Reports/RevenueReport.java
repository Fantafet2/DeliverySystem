package Reports;

import generateReports.reportgeneration;

public class RevenueReport extends report implements reportgeneration{

	public RevenueReport() {
		super("REVENUE REPORT");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getsql() {
		// TODO Auto-generated method stub
		return "SELECT DATE(payment_date) AS Date, SUM(Amount) AS Revenue"
				+ "FROM Payments GROUP BY DATE(Payment_date) ORDER BY DATE(payment_date)";
	}

	@Override
	protected String[] getreportHeader() {
		// TODO Auto-generated method stub
		return new String[] {"Date", "Revenue"};
	}

	@Override
	protected String getFilename() {
		// TODO Auto-generated method stub
		return "RevenueReport.pdf";
	}

}
