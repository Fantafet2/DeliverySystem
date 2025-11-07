package group_v1;

import java.sql.*;
import java.util.Scanner;

import javax.swing.JTextField;


public class SendToDatabase {

 
	public static void sendRequestData(String sendersname, String receivererName, String packageweight, String packagedemesion,String deliveryoption,String deliveryzoneoptions, String pickupzoneoptions, String destination) {
		String url = "jdbc:mysql://localhost:3306/shippingdb";
		Connection myConn = null;
		

		try{
			myConn = DriverManager.getConnection(url,"root","");
			
			int weight = 0;
			try {
		        weight = Integer.parseInt(packageweight);
		    } catch (NumberFormatException ex) {
		        System.err.println("Error: Please enter only valid numbers for weight.");
		    }
			
	            // Use a prepared statement to avoid SQL injection
	            String sql = "INSERT INTO shippingrequest (sendersname, receivererName, packageweight, packagedemesion, deliveryoption, destination,deliveryzoneoptions, pickupzoneoptions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement pstmt = myConn.prepareStatement(sql);
	            pstmt.setString(1, sendersname);
	            pstmt.setString(2, receivererName);
	            pstmt.setInt(3, weight);
	            pstmt.setString(4, packagedemesion);
	            pstmt.setString(5, deliveryoption);
	            pstmt.setString(6, destination);
	            pstmt.setString(7, deliveryzoneoptions);
	            pstmt.setString(8, pickupzoneoptions);


	            int rows = pstmt.executeUpdate();

	            if (rows > 0) {
	                System.out.println("✅ User registered successfully!");
	            }

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	public static void sendDriverData(String driverName, String driverZone) {
		String url = "jdbc:mysql://localhost:3306/shippingdb";
		Connection myConn = null;
		
		try{
			myConn = DriverManager.getConnection(url,"root","");
			
	            // Use a prepared statement to avoid SQL injection
	            String sql = "INSERT INTO driver (driverName, driverZone) VALUES (?, ?)";
	            PreparedStatement pstmt = myConn.prepareStatement(sql);
	            pstmt.setString(1, driverName);
	            pstmt.setString(2, driverZone);
	            
	            
	            int rows = pstmt.executeUpdate();

	            if (rows > 0) {
	                System.out.println("✅ Driver registered successfully!");
	            }

			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	

	public static void sendDeliveryData(String SdriverID, String SrequestID, String driverName, String deliveryAdd,String recipientName, String type) {
		String url = "jdbc:mysql://localhost:3306/shippingdb";
		Connection myConn = null;
		
		String status = "Assigned";
				
		int driverID = Integer.parseInt(SdriverID);
		int requestID = Integer.parseInt(SrequestID);
		
		try {
			myConn = DriverManager.getConnection(url,"root","");
			
			String sql = "INSERT INTO delivery (driverID, requestID, driverName, deliveryAdd, recipientName, type, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = myConn.prepareStatement(sql);
			pstmt.setInt(1, driverID);
			pstmt.setInt(2, requestID);
			pstmt.setString(3, driverName);
			pstmt.setString(4, deliveryAdd);
			pstmt.setString(5, recipientName);
			pstmt.setString(6, type);
			pstmt.setString(7, status);
			
            int rows = pstmt.executeUpdate();

			if (rows > 0) {
                System.out.println("✅ Driver registered successfully!");
            }


		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
