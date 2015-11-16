package epam.java.task1.running;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.exceptions.EmptyCollectionException;
import epam.java.task1.exceptions.WrongPowerException;
import epam.java.task1.exceptions.WrongDeviceTypeException;
import epam.java.task1.exceptions.WrongPluggedInValue;
import epam.java.task1.instances.Database;
import epam.java.task1.instances.TextFile;
import epam.java.task1.instances.Xml;

public class Running {

	public static void main(String[] args) throws IOException, WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException, SQLException, ParserConfigurationException, SAXException, TransformerException {

		CollectHomeDevices devices = new CollectHomeDevices();
		System.out.println("-------- Start --------");
		System.out.println("Please select input instance:" + "\n 1: Text file" + "\n 2: Database" + "\n 3: XML file");
		Scanner scanner = new Scanner(System.in);
		String instance = scanner.nextLine();
		
		switch (instance) {
		case "1":
			TextFile textFile = new TextFile("src/main/resources/input/devices", "src/main/resources/output/results");
			devices = textFile.readFromInstance();
			devices.sortDevicesListByName();
			textFile.writeToInstance(devices);
			textFile.writePowerToFile(devices.calculatePower());		
			try{
				textFile.writeFoundByCriteriaDeviceToFile(devices.findByCriteria(textFile.readCriteriaFromFile()));
			}
			catch (EmptyCollectionException e) { 
				e.printStackTrace(); 
			}
			break;
		case "2":
			Database readFromDB = new Database("jdbc:derby:devices_db;create=true;user=me;password=me");
			devices = readFromDB.readFromInstance();
			devices.sortDevicesListByName();
			readFromDB.writeToInstance(devices);
			break;
		case "3":
			Xml readFromXml = new Xml("src/main/resources/input/devices_xml.xml", "src/main/resources/output/results_xml.xml");
			devices = readFromXml.readFromInstance();
			devices.sortDevicesListByName();
			readFromXml.writeToInstance(devices);
			break;
		default:
			System.out.println("Specified instance is not valid!");
		}
		
		System.out.println("-------- Finish --------");
		
	}
}
