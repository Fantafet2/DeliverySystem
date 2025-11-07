package driver;

import javax.swing.*;

import group_v1.GetInfo;
import group_v1.SendToDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class DriverActionPage extends JFrame {

	public DriverActionPage() {
		super("Driver actions");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,800);
		setLayout(new BorderLayout(10,10));
		 
		JLabel driverinfoInputlb;
		
		JLabel drivernamelb;
		JTextField driverNametf;
		
		JLabel driverZonelb;
		JTextField driverZonetf;
		
		JButton createDriverbtn;
		
		JPanel driverpnl;
		
		driverpnl = new JPanel();
		driverpnl.setLayout(new BoxLayout(driverpnl, BoxLayout.Y_AXIS));
		driverpnl.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		add(driverpnl);
	 	
		driverinfoInputlb = new JLabel("Enter Drivers Information below");
		driverinfoInputlb.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverpnl.add(driverinfoInputlb);
		
		drivernamelb = new JLabel("Enter Driver Name");
		drivernamelb.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverpnl.add(drivernamelb);
		
		driverNametf = new JTextField(20);
		driverNametf.setAlignmentX(Component.CENTER_ALIGNMENT);
		Dimension driverNameSize = driverNametf.getPreferredSize();
		driverNametf.setMaximumSize(driverNameSize);
		driverNametf.setName("driverName");
		driverpnl.add(driverNametf);

		driverZonelb = new JLabel("Enter Driver Name");
		driverZonelb.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverpnl.add(driverZonelb);
		
		driverZonetf = new JTextField(20);
		driverZonetf.setAlignmentX(Component.CENTER_ALIGNMENT);
		Dimension driverZoneSize = driverZonetf.getPreferredSize();
		driverZonetf.setMaximumSize(driverZoneSize);
		driverZonetf.setName("driverZone");
		driverpnl.add(driverZonetf);

		createDriverbtn = new JButton("Create Driver");
		driverpnl.add(createDriverbtn);

		createDriverbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<JComponent> addNewDriver = List.of(driverNametf, driverZonetf);				
				Map<String, String> driverData = GetInfo.collectInput(addNewDriver);
				
				String driverName = driverData.get("driverName");
				String driverZone = driverData.get("driverZone");
				
				
				SendToDatabase.sendDriverData(driverName, driverZone);
			}
		});
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
