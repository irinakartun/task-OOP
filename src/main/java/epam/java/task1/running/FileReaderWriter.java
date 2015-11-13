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

	public static CollectHomeDevices readDevicesFromFile() throws IOException, // method
																				// of
																				// device
																				// reading
																				// from
																				// file
			WrongDeviceTypeException, WrongPluggedInValue, WrongPowerException {
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		CollectHomeDevices collectedDevices = new CollectHomeDevices();
		collectedDevices.setDevicesList(devicesList);
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader("d:\\devices.txt"));
			String l;
			while ((l = inputStream.readLine()) != null) {
				String[] line = l.split("/");

				if (!(line[0].equalsIgnoreCase("beauty gadget"))
						&& !(line[0].equalsIgnoreCase("ñlimat equipment"))
						&& !(line[0].equalsIgnoreCase("household appliance"))) {
					throw new WrongDeviceTypeException(line[0]); // exception
																	// if the
																	// device
																	// type
																	// doesn't
																	// exist
				} else {

					switch (line[0].toLowerCase()) {
					case "beauty gadget":
						if (!(line[4].equalsIgnoreCase("true"))
								&& !(line[4].equalsIgnoreCase("false"))) {
							throw new WrongPluggedInValue(line[4]); // exception
																	// if the
																	// value of
																	// IsPluggedIn
																	// is
																	// invalid
						} else {
							if (!line[3].matches("^[0-9]\\d*$")) {
								throw new WrongPowerException(line[3]); // negative
																		// o
																		// non-integer
																		// value
																		// of
																		// power
							} else {
								ElectricDevices bGadget = new BeautyGadgets(
										line[1], line[2],
										Integer.parseInt(line[3]),
										Boolean.parseBoolean(line[4]), line[5]);
								collectedDevices.getDevicesList().add(bGadget);
							}
						}
						break;
					case "ñlimat equipment":
						if (!(line[4].equalsIgnoreCase("true"))
								&& !(line[4].equalsIgnoreCase("false"))) {
							throw new WrongPluggedInValue(line[4]); // exception
																	// if the
																	// value of
																	// IsPluggedIn
																	// is
																	// invalid
						} else {
							if (!line[3].matches("^[0-9]\\d*$")) {
								throw new WrongPowerException(line[3]); // negative
																		// o
																		// non-integer
																		// value
																		// of
																		// power
							} else {
								ElectricDevices cGadget = new ClimatEquipment(
										line[1], line[2],
										Integer.parseInt(line[3]),
										Boolean.parseBoolean(line[4]), line[5]);
								collectedDevices.getDevicesList().add(cGadget);
							}
						}
						break;
					case "household appliance":
						if (!(line[4].equalsIgnoreCase("true"))
								&& !(line[4].equalsIgnoreCase("false"))) {
							throw new WrongPluggedInValue(line[4]); // exception
																	// if the
																	// value of
																	// IsPluggedIn
																	// is
																	// invalid
						} else {
							if (!line[3].matches("^[0-9]\\d*$")) {
								throw new WrongPowerException(line[3]); // negative
																		// o
																		// non-integer
																		// value
																		// of
																		// power
							} else {
								ElectricDevices hGadget = new ClimatEquipment(
										line[1], line[2],
										Integer.parseInt(line[3]),
										Boolean.parseBoolean(line[4]), line[5]);
								collectedDevices.getDevicesList().add(hGadget);
							}
						}
						break;
					default:
						System.out.println("Specified device is not valid!");
					}

				}
			}
		} catch (FileNotFoundException e) { // catch if input file is not found
			e.printStackTrace();
		} catch (IOException e) { // catch if there's an error during file
									// reading
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return collectedDevices;
	}

	public static void writeSortingResultsToFile(
			CollectHomeDevices collectedDevices) throws IOException { // method
																		// for
																		// writing
																		// sort
																		// results
																		// to
																		// file
		try {
			outputStream = new PrintWriter(new FileWriter("d:\\results.txt"));
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

	public static void writePowerToFile(int power) throws IOException { // method
																		// for
																		// writing
																		// total
																		// power
																		// to
																		// file
		try {
			outputStream = new PrintWriter(new FileWriter("d:\\results.txt",
					true));
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

	public static String[] readCriteriaFromFile() throws IOException { // method
																		// for
																		// reading
																		// search
																		// criteria
																		// from
																		// file
		BufferedReader inputStream = null;
		String[] criteria = null;
		try {
			inputStream = new BufferedReader(new FileReader("d:\\criteria.txt"));
			String line;
			while ((line = inputStream.readLine()) != null) {
				criteria = line.split("/");
			}
		} catch (FileNotFoundException e) { // catch if input file is not found
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
			EmptyCollectionException { // method for writing search results to
										// file
		try {
			if (correspDevices.isEmpty()) {
				throw new EmptyCollectionException(
						"There are no devices that correspond your criteria!"); // if
																				// empty
																				// collection
																				// is
																				// passed
																				// into
																				// the
																				// method
			}
			outputStream = new PrintWriter(new FileWriter("d:\\results.txt",
					true));
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

}
