package epam.java.task1.instances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.derby.jdbc.EmbeddedDriver;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;

public class Database implements Instance {
	
	public String connectionString;
	
	
	public Database(String connectionString){
		this.connectionString = connectionString;
	}
	

	@Override
	public CollectHomeDevices readFromInstance() throws SQLException {

		DriverManager.registerDriver(new EmbeddedDriver());
		try(
				Connection connection = DriverManager.getConnection(connectionString);
				Statement statement = connection.createStatement();
		) {
			ResultSet resultSet = statement
					.executeQuery("SELECT TYPE, NAME, COLOUR, POWER, PLUGGEDIN, ATTRIBUTE FROM devices INNER JOIN device_type on devices.TYPE_ID=device_type.ID");
	
			ResultSetMetaData metadata = resultSet.getMetaData();
			
			ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
			CollectHomeDevices collectedDevices = new CollectHomeDevices();
			collectedDevices.setDevicesList(devicesList);
	
			while (resultSet.next()) {
				
				switch (resultSet.getString(metadata.getColumnName(1)).toLowerCase()) {
				case "beauty gadget":
					ElectricDevices beautyDevice = new BeautyGadgets(
							resultSet.getString(metadata.getColumnName(2)),
							resultSet.getString(metadata.getColumnName(3)),
							Integer.parseInt(resultSet.getString(metadata.getColumnName(4))),
							Boolean.parseBoolean(resultSet.getString(metadata.getColumnName(5))),
							resultSet.getString(metadata.getColumnName(6)));
					collectedDevices.getDevicesList().add(beautyDevice);
					break;
				case "ñlimat equipment":
					ElectricDevices climatDevice = new ClimatEquipment(
							resultSet.getString(metadata.getColumnName(2)),
							resultSet.getString(metadata.getColumnName(3)),
							Integer.parseInt(resultSet.getString(metadata.getColumnName(4))),
							Boolean.parseBoolean(resultSet.getString(metadata.getColumnName(5))),
							resultSet.getString(metadata.getColumnName(6)));
					collectedDevices.getDevicesList().add(climatDevice);
					break;
				case "household appliance":
					ElectricDevices houseDevice = new HouseholdAppliances(
							resultSet.getString(metadata.getColumnName(2)),
							resultSet.getString(metadata.getColumnName(3)),
							Integer.parseInt(resultSet.getString(metadata.getColumnName(4))),
							Boolean.parseBoolean(resultSet.getString(metadata.getColumnName(5))),
							resultSet.getString(metadata.getColumnName(6)));
					collectedDevices.getDevicesList().add(houseDevice);
					break;
				default:
					System.out.println("Specified device type is invalid!");
				}
				
			}
		
			return collectedDevices;
		}
	}


	@Override
	public void writeToInstance(CollectHomeDevices collectedDevices) throws SQLException {
		
		DriverManager.registerDriver(new EmbeddedDriver());
		try(
				Connection connection = DriverManager.getConnection(connectionString);
				PreparedStatement prep = connection.prepareStatement("INSERT INTO results (NAME, COLOUR, POWER, PLUGGEDIN) VALUES (?, ?, ?, ?)");
			) {
		
			for (int i = 0; i < collectedDevices.getDevicesList().size(); i++){
				String name = collectedDevices.getDevicesList().get(i).getName();
				String colour = collectedDevices.getDevicesList().get(i).getColour();
				int power = collectedDevices.getDevicesList().get(i).getDevicePower();
				boolean pluggedIn = collectedDevices.getDevicesList().get(i).getPluggedIn();
				prep.setString(1, name);
				prep.setString(2, colour);
				prep.setInt(3, power);
				prep.setBoolean(4, pluggedIn);
				prep.executeUpdate();
			}
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM results");
			ResultSetMetaData meta = resultSet.getMetaData();
			
			System.out.println("Written to database ordered devices:");
			
			while(resultSet.next()){
				System.out.println("\n");
				for(int i = 1; i<=meta.getColumnCount(); i++){
					System.out.println(resultSet.getString(i).toString());
				}	
			}
		}
		cleanDBResults();
	}
	
	
	public void cleanDBResults() throws SQLException {
		DriverManager.registerDriver(new EmbeddedDriver());
		try(
				Connection connection = DriverManager.getConnection(connectionString);
				Statement statement = connection.createStatement();
			) {
			int resultSet = statement.executeUpdate("DELETE FROM results");
		}
	}
	
		

}
