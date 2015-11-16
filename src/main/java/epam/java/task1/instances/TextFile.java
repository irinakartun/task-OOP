package epam.java.task1.instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.exceptions.EmptyCollectionException;
import epam.java.task1.exceptions.WrongPowerException;
import epam.java.task1.exceptions.WrongDeviceTypeException;
import epam.java.task1.exceptions.WrongPluggedInValue;

public class TextFile implements Instance{

	PrintWriter outputStream = null;
	public String inputFile;
	public String outputFile;
	
	public TextFile(String inputFile, String outputFile){
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	@Override
	public CollectHomeDevices readFromInstance() throws IOException,
			WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException {
		
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		CollectHomeDevices collectedDevices = new CollectHomeDevices();
		collectedDevices.setDevicesList(devicesList);
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(inputFile));
			String l;
			while ((l = inputStream.readLine()) != null) {
				String[] line = l.split("/");

				if (!(line[0].equalsIgnoreCase("beauty gadget")) && !(line[0].equalsIgnoreCase("ñlimat equipment"))	&& !(line[0].equalsIgnoreCase("household appliance"))) {
					throw new WrongDeviceTypeException("Specified device type:" + line[0] + " is not valid!");
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

	
	@Override
	public void writeToInstance(
			CollectHomeDevices collectedDevices) throws IOException {
		try {
			outputStream = new PrintWriter(new FileWriter(outputFile));
			outputStream.println("\nList of devices sorted by name:");
			for (int i = 0; i < collectedDevices.getDevicesList().size(); i++) {
				outputStream.println(collectedDevices.getDevicesList().get(i).getInfo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	
	public void writePowerToFile(int power) throws IOException {
		try {
			outputStream = new PrintWriter(new FileWriter(outputFile, true));
			outputStream.println("\nTotal power of plugged in devices is: " + power);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	
	public String[] readCriteriaFromFile() throws IOException {
		BufferedReader inputStream = null;
		String[] criteria = null;
		try {
			inputStream = new BufferedReader(new FileReader("src/main/resources/input/criteria"));
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

	
	public void writeFoundByCriteriaDeviceToFile(
			ArrayList<ElectricDevices> correspDevices) throws IOException,
			EmptyCollectionException {
		try {
			if (correspDevices.isEmpty()) {
				throw new EmptyCollectionException("There are no devices that correspond your criteria!");
			}
			outputStream = new PrintWriter(new FileWriter(outputFile, true));
			outputStream.println("\nThe list of devices that correspond criteria:");
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

	public void wrongIsPluggedInValue(String isPluggedIn) {
		if (!(isPluggedIn.equalsIgnoreCase("true"))
				&& !(isPluggedIn.equalsIgnoreCase("false"))) {
			throw new WrongPluggedInValue("Specified value of isPluggedIn: " + isPluggedIn + " is not valid!");
		}
	}

	public void wrongPowerValue(String power) {
		if (!power.matches("^[0-9]\\d*$")) {
			throw new WrongPowerException("Specified device power: " + power + " is invalid!");
		}
	}

}
