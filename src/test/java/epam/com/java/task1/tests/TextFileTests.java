package epam.com.java.task1.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;
import epam.java.task1.exceptions.EmptyCollectionException;
import epam.java.task1.exceptions.WrongDeviceTypeException;
import epam.java.task1.exceptions.WrongPluggedInValue;
import epam.java.task1.exceptions.WrongPowerException;


public class TextFileTests extends TextFileBasicTest{

	@Test
    public void readFromTextFileTest() throws WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException, IOException{
		
		textFile.inputFile = "src/main/resources/input/devices";
		
		CollectHomeDevices resultFromFile = textFile.readFromInstance();
		
		CollectHomeDevices expDevices = new CollectHomeDevices();
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		BeautyGadgets device1 = new BeautyGadgets("hair dryer", "black", 75, true, "female");
		devicesList.add(device1);
		ClimatEquipment device2 = new ClimatEquipment("air condition", "white", 250, true, "cooling");
		devicesList.add(device2);
		HouseholdAppliances device3 = new HouseholdAppliances("kettle", "green", 100, false, "kitchen");
		devicesList.add(device3);
		expDevices.setDevicesList(devicesList);

		for (int i = 0; i < resultFromFile.getDevicesList().size(); i++){
			assertTrue((resultFromFile.getDevicesList().get(i).getName()).equals(expDevices.getDevicesList().get(i).getName()), "Invalid device name!");
			assertTrue((resultFromFile.getDevicesList().get(i).getColour()).equals(expDevices.getDevicesList().get(i).getColour()), "Invalid device colour!");
			assertTrue((resultFromFile.getDevicesList().get(i).getDevicePower()) == (expDevices.getDevicesList().get(i).getDevicePower()), "Invalid device power!");
			assertTrue((resultFromFile.getDevicesList().get(i).getPluggedIn()) == (expDevices.getDevicesList().get(i).getPluggedIn()), "Invalid device plugged in!");
		}
    }
	
	@Test(expectedExceptions = {WrongDeviceTypeException.class})
    public void wrongDeviceTypeTest() throws WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException, IOException {
		textFile.inputFile = "src/main/resources/input/wrongType";
		textFile.readFromInstance();
    }
	
	@Test(expectedExceptions = {WrongPluggedInValue.class})
    public void wrongPluggedInTest() throws WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException, IOException {
		textFile.inputFile = "src/main/resources/input/wrongPluggedIn";
		textFile.readFromInstance();
    }
	
	@Test(expectedExceptions = {WrongPowerException.class})
    public void wrongPowerTest() throws WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException, IOException {
		textFile.inputFile = "src/main/resources/input/wrongPower";
		textFile.readFromInstance();
    }
	
	@Test(dataProvider = "dataProviderForWriteToTextFile")
    public void writeToTextFileTest(String name, String colour, int power, boolean isPlugged, String gender) throws IOException{
		
		textFile.outputFile = "src/main/resources/output/written_to_file";
		CollectHomeDevices result = new CollectHomeDevices();
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		ElectricDevices device = new BeautyGadgets(name, colour, power, isPlugged, gender);
		devicesList.add(device);
		result.setDevicesList(devicesList);
		textFile.writeToInstance(result);
		File f = new File("src/main/resources/output/written_to_file");
		
		assertTrue(f.exists(), "Devices were not written to file!"); 
    }
	
	@DataProvider(name = "dataProviderForWriteToTextFile")
    public Object[][] dataProviderWriteToTextFile(){
        return new Object[][]{
                {"razor", "black", 75, true, "female"},
                {"device with spaces", "bla ck", 0, false, "male"},
                {"device&&special", "whi$te", 56, false, "male"},
                {"", "", 56, false, ""}};
    }
	
	
	@Test(dataProvider = "dataProviderForWritePowerToFile")
    public void writePowerToFileTest(int power) throws IOException{
		textFile.outputFile = "src/main/resources/output/power";
		textFile.writePowerToFile(power);
		File f = new File("src/main/resources/output/power");
		assertTrue(f.exists(), "Devices were not written to file!"); 
    }
	
	@DataProvider(name = "dataProviderForWritePowerToFile")
    public Object[][] dataProviderWritePowerToFile(){
        return new Object[][]{
                {60},
                {0},
                {-10}};
    }
	
	@Test
    public void readCriteriaFromFile() throws IOException {
		textFile.inputFile = "src/main/resources/input/criteria";
		String[] result = textFile.readCriteriaFromFile();
		assertTrue(result[0].equals("white"), "Invalid reading from file!");
		assertTrue(result[1].equals("true"), "Invalid reading from file!");
		assertTrue(result[2].equals("10"), "Invalid reading from file!");
    }
	
	@Test(dataProvider = "dataProviderForWriteToFileCriteria")
    public void writeToFileCriteriaTest(String name, String colour, int power, boolean isPlugged, String attrib) throws IOException, EmptyCollectionException {
		
		textFile.outputFile = "src/main/resources/output/written_criteria_to_file";
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		ElectricDevices device = new BeautyGadgets(name, colour, power, isPlugged, attrib);
		devicesList.add(device);
		textFile.writeFoundByCriteriaDeviceToFile(devicesList);
		File f = new File("src/main/resources/output/written_criteria_to_file");
		
		assertTrue(f.exists(), "Devices were not written to the file!"); 
    }
	
	@DataProvider(name = "dataProviderForWriteToFileCriteria")
    public Object[][] dataProviderWriteToFileCriteria(){
        return new Object[][]{
                {"razor", "black", 75, true, "female"},
                {"device with spaces", "bla ck", 0, false, "male"},
                {"device&&special", "whi$te", 56, false, "male"},
                {"", "", 56, false, ""}};
    }
	
	@Test(expectedExceptions = EmptyCollectionException.class)
    public void emptyCollectionExceptionTest() throws IOException, EmptyCollectionException {
		textFile.outputFile = "src/main/resources/output/written_criteria_to_file";
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		textFile.writeFoundByCriteriaDeviceToFile(devicesList);
    }

}
