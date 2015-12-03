package epam.com.java.task1.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;


public class XmlTests extends XmlBasicTest{

	@Test
    public void readFromXmlTest() throws ParserConfigurationException, SAXException, IOException {
		
		xmlFile.xmlInputPath = "src/main/resources/input/devices_xml.xml";
		CollectHomeDevices resultFromXml = xmlFile.readFromInstance();
		
		CollectHomeDevices expDevices = new CollectHomeDevices();
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		BeautyGadgets device1 = new BeautyGadgets("washing machine", "white", 350, true, "for bathroom");
		devicesList.add(device1);
		ClimatEquipment device2 = new ClimatEquipment("razor", "black", 75, false, "male");
		devicesList.add(device2);
		HouseholdAppliances device3 = new HouseholdAppliances("heater", "green", 220, true, "for heating");
		devicesList.add(device3);
		expDevices.setDevicesList(devicesList);

		for (int i = 0; i < resultFromXml.getDevicesList().size(); i++){
			assertTrue((resultFromXml.getDevicesList().get(i).getName()).equals(expDevices.getDevicesList().get(i).getName()), "Invalid device name!");
			assertTrue((resultFromXml.getDevicesList().get(i).getColour()).equals(expDevices.getDevicesList().get(i).getColour()), "Invalid device colour!");
			assertTrue((resultFromXml.getDevicesList().get(i).getDevicePower()) == (expDevices.getDevicesList().get(i).getDevicePower()), "Invalid device power!");
			assertTrue((resultFromXml.getDevicesList().get(i).getPluggedIn()) == (expDevices.getDevicesList().get(i).getPluggedIn()), "Invalid device plugged in!");
		}
    }
	
	
	@Test(dataProvider = "dataProviderForWriteToXmlFile")
    public void writeToXmlTest(String name, String colour, int power, boolean isPlugged, String gender) throws IOException, ParserConfigurationException, SAXException, TransformerException{
		
		xmlFile.xmlOutputPath = "src/main/resources/output/written_to_xml";

		CollectHomeDevices result = new CollectHomeDevices();
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		ElectricDevices device = new BeautyGadgets(name, colour, power, isPlugged, gender);
		devicesList.add(device);
		result.setDevicesList(devicesList);
		xmlFile.writeToInstance(result);
		File f = new File("src/main/resources/output/written_to_xml");
		
		assertTrue(f.exists(), "Devices were not written to xml!"); 
    }
	
	@DataProvider(name = "dataProviderForWriteToXmlFile")
    public Object[][] dataProviderWriteToXml(){
        return new Object[][]{
                {"razor", "black", 75, true, "female"},
                {"device with spaces", "bla ck", 0, false, "male"},
                {"device&&special", "whi$te", 56, false, "male"},
                {"", "", 56, false, ""}};
    }
	
}
