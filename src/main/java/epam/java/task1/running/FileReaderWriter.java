package epam.java.task1.running;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.exceptions.EmptyCollectionException;
import epam.java.task1.exceptions.WrongPowerException;
import epam.java.task1.exceptions.WrongDeviceTypeException;
import epam.java.task1.exceptions.WrongPluggedInValue;
import epam.java.task1.homeDevices.BeautyGadgets;
import epam.java.task1.homeDevices.ClimatEquipment;

public class FileReaderWriter {

	static PrintWriter outputStream = null;

	public static CollectHomeDevices readDevicesFromFile() throws IOException,
			WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException {
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		CollectHomeDevices collectedDevices = new CollectHomeDevices();
		collectedDevices.setDevicesList(devicesList);
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(
					"src/main/resources/input/devices"));
			String l;
			while ((l = inputStream.readLine()) != null) {
				String[] line = l.split("/");

				if (!(line[0].equalsIgnoreCase("beauty gadget"))
						&& !(line[0].equalsIgnoreCase("ñlimat equipment"))
						&& !(line[0].equalsIgnoreCase("household appliance"))) {
					throw new WrongDeviceTypeException("Specified device type:"
							+ line[0] + " is not valid!");
				} else {

					switch (line[0].toLowerCase()) {
					case "beauty gadget":
						wrongIsPluggedInValue(line[4]);
						wrongPowerValue(line[3]);
						ElectricDevices bGadget = new BeautyGadgets(line[1],
								line[2], Integer.parseInt(line[3]),
								Boolean.parseBoolean(line[4]), line[5]);
						collectedDevices.getDevicesList().add(bGadget);
						break;
					case "ñlimat equipment":
						wrongIsPluggedInValue(line[4]);
						wrongPowerValue(line[3]);
						ElectricDevices cGadget = new ClimatEquipment(line[1],
								line[2], Integer.parseInt(line[3]),
								Boolean.parseBoolean(line[4]), line[5]);
						collectedDevices.getDevicesList().add(cGadget);
						break;
					case "household appliance":
						wrongIsPluggedInValue(line[4]);
						wrongPowerValue(line[3]);
						ElectricDevices hGadget = new ClimatEquipment(line[1],
								line[2], Integer.parseInt(line[3]),
								Boolean.parseBoolean(line[4]), line[5]);
						collectedDevices.getDevicesList().add(hGadget);
						break;
					default:
						System.out.println("Specified device is not valid!");
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return collectedDevices;
	}

	public static void writeSortingResultsToFile(
			CollectHomeDevices collectedDevices) throws IOException {
		try {
			outputStream = new PrintWriter(new FileWriter(
					"src/main/resources/output/results"));
			for (int i = 0; i < collectedDevices.getDevicesList().size(); i++) {
				outputStream.println(collectedDevices.getDevicesList().get(i)
						.getInfo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void writePowerToFile(int power) throws IOException {
		try {
			outputStream = new PrintWriter(new FileWriter(
					"src/main/resources/output/results", true));
			outputStream.println("Total power of plugged in devices is: "
					+ power);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static String[] readCriteriaFromFile() throws IOException {
		BufferedReader inputStream = null;
		String[] criteria = null;
		try {
			inputStream = new BufferedReader(new FileReader(
					"src/main/resources/input/criteria"));
			String line;
			while ((line = inputStream.readLine()) != null) {
				criteria = line.split("/");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return criteria;
	}

	public static void writeFoundByCriteriaDeviceToFile(
			ArrayList<ElectricDevices> correspDevices) throws IOException,
			EmptyCollectionException {
		try {
			if (correspDevices.isEmpty()) {
				throw new EmptyCollectionException(
						"There are no devices that correspond your criteria!");
			}
			outputStream = new PrintWriter(new FileWriter(
					"src/main/resources/output/results", true));
			outputStream
					.println("The list of devices that correspond criteria:");
			for (int i = 0; i < correspDevices.size(); i++) {
				outputStream.println(correspDevices.get(i).getInfo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void wrongIsPluggedInValue(String isPluggedIn) {
		if (!(isPluggedIn.equalsIgnoreCase("true"))
				&& !(isPluggedIn.equalsIgnoreCase("false"))) {
			throw new WrongPluggedInValue("Specified value of isPluggedIn: "
					+ isPluggedIn + " is not valid!");
		}
	}

	public static void wrongPowerValue(String power) {
		if (!power.matches("^[0-9]\\d*$")) {
			throw new WrongPowerException("Specified device power: " + power
					+ " is invalid!");
		}
	}

}
