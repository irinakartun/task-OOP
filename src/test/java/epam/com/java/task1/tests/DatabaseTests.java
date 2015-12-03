package epam.com.java.task1.tests;

import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;


public class DatabaseTests extends DatabaseBasicTest{

	@Test
    public void readFromDb() throws SQLException {
		
		database.connectionString = "jdbc:derby:devices_db;create=true;user=me;password=me";
		CollectHomeDevices result = database.readFromInstance();
		
		ArrayList<ElectricDevices> expResult = new ArrayList<ElectricDevices>();
		ElectricDevices device3 = new HouseholdAppliances("kettle", "green", 100, false, "kitchen");
		ElectricDevices device1 = new BeautyGadgets("hair dryer", "black", 75, true, "female");
		ElectricDevices device2 = new ClimatEquipment("air condition", "white", 250, true, "cooling");
		expResult.add(device1);
		expResult.add(device2);
		expResult.add(device3);
		
		for (int i = 0; i < result.getDevicesList().size(); i++){
			assertTrue((result.getDevicesList().get(i).getName()).equals(expResult.get(i).getName()), "Invalid device name!");
			assertTrue((result.getDevicesList().get(i).getColour()).equals(expResult.get(i).getColour()), "Invalid device colour!");
			assertTrue((result.getDevicesList().get(i).getDevicePower()) == (expResult.get(i).getDevicePower()), "Invalid device power!");
			assertTrue((result.getDevicesList().get(i).getPluggedIn()) == (expResult.get(i).getPluggedIn()), "Invalid device plugged in!");
		}
		
    }
	
	@Test(expectedExceptions = {SQLException.class})
    public void writeToDbCannotConnect() throws SQLException {
		
		database.connectionString = "jdbc:derby:devices_db_invalid;create=true;user=me;password=me";
		CollectHomeDevices devicesToDb = new CollectHomeDevices();
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		devicesToDb.setDevicesList(devicesList);
		ElectricDevices device = new BeautyGadgets("hair dryer", "black", 75, true, "female");
		devicesList.add(device);
		database.writeToInstance(devicesToDb);
    }
	

/*	
	@Test
    public void writeToDb(String name, String colour, int power, boolean isPlugged, String gender) throws SQLException{
		
		CollectHomeDevices devicesToDb = new CollectHomeDevices();
		BeautyGadgets device = new BeautyGadgets(name, colour, power, isPlugged, gender);
		devicesToDb.getDevicesList().add(device);
		database.writeToInstance(devicesToDb);
		
		
		DriverManager.registerDriver(new EmbeddedDriver());
		Connection connection = DriverManager.getConnection(database.connectionString);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT NAME, COLOUR, POWER, PLUGGEDIN FROM results");
		ResultSetMetaData metadata = resultSet.getMetaData();
		
    }
	
*/	

}
