package Reports;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import generateReports.reportgeneration;

public abstract class report implements reportgeneration {
	 protected Connection conn; 
	 protected String reportheader; 
	 
	 public report (String reportheader) {
		 this.conn = dbconnection.getconnection(); 
		 this.reportheader = reportheader; 
		 
	 }
	 
	 protected abstract String getsql(); 
	 protected abstract String[] getreportHeader(); 
	 protected abstract String getFilename();
	 
	 public void GenerateReport() {
		 try {
			 Statement statement = conn.createStatement(); 
			 ResultSet result = statement.executeQuery(getsql()); 
			 
			 //creating  PDP document 
			 Document document = new Document(); 
			 PdfWriter.getInstance(document, new FileOutputStream(getFilename())); 
			 document.open(); 
			 
			 document.add(new Paragraph(reportheader + '\n')); 
			 document.add(new Paragraph(" SMARTSHIP REPORTING SYSTEM"));
			 document.add(new Paragraph("---------------------------------------")); 
			 
			 String[] reportheader= getreportHeader(); 
			 PdfPTable table = new PdfPTable(reportheader.length); 
			 table.setWidthPercentage(100); 
			 
			 for (String header: reportheader) {
				 table.addCell(header);
			 }
			 
			 // Adding rows
			 int column = reportheader.length; 
			 while(result.next()) {
				 for (int i=1;  i<= column; i++ ) {
					 table.addCell(result.getString(i) != null ? result.getString(i):"N/A"); 
				 }
				 
			 }
			 //Add table to document
			 document.add(table);
			 document.close();
			 
			 System.out.printf("FILE SUCCESSFULLY EXPORTED:" + getFilename());
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
		
}
