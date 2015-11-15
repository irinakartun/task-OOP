package epam.java.task1.running;

import java.io.IOException;

import epam.java.task1.exceptions.EmptyCollectionException;
import epam.java.task1.exceptions.WrongPowerException;
import epam.java.task1.exceptions.WrongDeviceTypeException;
import epam.java.task1.exceptions.WrongPluggedInValue;

public class Running {

	public static void main(String[] args) throws IOException, WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException {

		System.out.println("start");
		CollectHomeDevices devicesFromFile = new CollectHomeDevices();
		
		devicesFromFile = FileReaderWriter.readDevicesFromFile();
		devicesFromFile.sortDevicesListByName();
		FileReaderWriter.writeSortingResultsToFile(devicesFromFile);
		FileReaderWriter.writePowerToFile(devicesFromFile.calculatePower());
//		devicesFromFile.findByCriteria(FileReaderWriter.readCriteriaFromFile());
		try {
			FileReaderWriter.writeFoundByCriteriaDeviceToFile(devices.findByCriteria(textFile.readCriteriaFromFile()));
		} catch (EmptyCollectionException e) {
			e.printStackTrace();
		}
		System.out.println("finish");
	}
}
