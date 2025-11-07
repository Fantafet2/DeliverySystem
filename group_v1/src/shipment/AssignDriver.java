package shipment;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group_v1.GetFromDatabase;
import group_v1.SendToDatabase;

public class AssignDriver extends JFrame{
	
	public AssignDriver() {
		super("Assign Driver");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,800);
		setLayout(new BorderLayout(10,10));
		
		JLabel assigntag;
		JLabel enterReqIDlb;
		JTextField enterReqIDtf;
		JButton assign;
		
		JPanel assignDriverpnl;
		assignDriverpnl = new JPanel();
		assignDriverpnl.setLayout(new BoxLayout(assignDriverpnl, BoxLayout.Y_AXIS));
		assignDriverpnl.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		add(assignDriverpnl);
		
		assigntag = new JLabel("Assign a Driver to a delivery");
		assignDriverpnl.add(assigntag);
		
		enterReqIDlb = new JLabel("Enter a transaction ID");
		assignDriverpnl.add(enterReqIDlb);
		
		enterReqIDtf = new JTextField(20);
		enterReqIDtf.setName("enterReqID");
		assignDriverpnl.add(enterReqIDtf);
		
		assign = new JButton("Find Driver");
		assignDriverpnl.add(assign);
		
		Dimension tfPreferredSize = enterReqIDtf.getPreferredSize();
		
		enterReqIDtf.setMaximumSize(tfPreferredSize);
		
		assign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String requestID = enterReqIDtf.getText();
				AssignDriver.AssignDriverToShipment(requestID);

			}
		});
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
		
	
	
	public static void AssignDriverToShipment(String requestID) {
		
		
		String zoneForReq = GetFromDatabase.getZoneByTransactionId(requestID);
		System.out.println("this is order id "+ requestID);

		
		List driverForZone = GetFromDatabase.getDriversByZone(zoneForReq);
		
		List requestinfo = GetFromDatabase.getRequestData(zoneForReq);
		
		
		getValuesForDeliveryTable(requestinfo,driverForZone);
		
		System.out.println("this is the list: "+ driverForZone);
	}



	private static void getValuesForDeliveryTable(List<String> requestinfo, List<String> driverForZone) {


		if (requestinfo.isEmpty()) {
	        System.out.println("Error: Request info list is empty.");
	        return;
	    }
	    String requestString = requestinfo.get(0);
	    
	 // Extract Request ID
	    String reqIdDelimiter = "Reques ID: ";
	    String nameDelimiter = " Name: ";
	    int reqIdStart = requestString.indexOf(reqIdDelimiter) + reqIdDelimiter.length();
	    int reqIdEnd = requestString.indexOf(nameDelimiter);
	    String requestId = requestString.substring(reqIdStart, reqIdEnd).trim();

	    // Extract Recipient Name
	    String weightDelimiter = " Weight: ";
	    int nameStart = reqIdEnd + nameDelimiter.length();
	    int nameEnd = requestString.indexOf(weightDelimiter);
	    String recipientName = requestString.substring(nameStart, nameEnd).trim();

	    // Extract Delivery Address
	    String addressDelimiter = " Address: ";
	    String zoneDelimiter = " Zone: ";
	    int addressStart = requestString.indexOf(addressDelimiter) + addressDelimiter.length();
	    int addressEnd = requestString.indexOf(zoneDelimiter);
	    String deliveryAddress = requestString.substring(addressStart, addressEnd).trim();

	    // Extract Type
	    String typeDelimiter = " Type: ";
	    int typeStart = requestString.indexOf(typeDelimiter) + typeDelimiter.length();
	    int typeEnd = requestString.indexOf(addressDelimiter); // Note: Address is the next delimiter
	    String type = requestString.substring(typeStart, typeEnd).trim();
	    
	    if (driverForZone.isEmpty()) {
	        System.out.println("Error: Driver info list is empty.");
	        return;
	    }
	    String driverString = driverForZone.get(0);
	    
	    // Extract Driver ID
	    String driverIdDelimiter = "ID: ";
	    String driverNameDelimiter = "Name: ";
	    int driverIdStart = driverString.indexOf(driverIdDelimiter) + driverIdDelimiter.length();
	    int driverIdEnd = driverString.indexOf(driverNameDelimiter);
	    String driverId = driverString.substring(driverIdStart, driverIdEnd).trim();
	    
	    // Extract Driver Name
	    String driverZoneDelimiter = ", Zone: ";
	    int driverNameStart = driverIdEnd + driverNameDelimiter.length();
	    int driverNameEnd = driverString.indexOf(driverZoneDelimiter);
	    String driverName = driverString.substring(driverNameStart, driverNameEnd).trim();


	    // --- Compile and Display Results ---
	    System.out.println("\n--- Extracted Shipment Details ---");
	    System.out.println("Driver ID:       " + driverId);
	    System.out.println("Request ID:      " + requestId);
	    System.out.println("Driver Name:     " + driverName);
	    System.out.println("Recipient Name:  " + recipientName);
	    System.out.println("Delivery Address:" + deliveryAddress);
	    System.out.println("Shipment Type:   " + type);
	    
	    
	    SendToDatabase.sendDeliveryData(driverId,requestId,driverName,deliveryAddress,recipientName,type);
	
	}




}
 








