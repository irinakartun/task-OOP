package epam.java.task1.running;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.homeDevices.BeautyGadgets;

public class CollectHomeDevices implements Comparator<ElectricDevices> {

	public static ArrayList<ElectricDevices> fillDevicesList(ArrayList<ElectricDevices> devicesList) {

		String deviceName;
		String deviceColour;
		String devicePower;
		String pluggedIn;
		String gender;
		String specialisation;
		String room;

		Scanner scanner = new Scanner(System.in);
		System.out.println("\nWhich device would you like to create?");
		System.out.println("1" + "\nBeauty gadget\n" + "2" + "\nClimat equipment\n" + "3" + "\nHousehold appliance\n");
		String device = scanner.nextLine(); // user chooses the object

		switch (device) {
		case "1":
			System.out.println("\nEnter device name");
			deviceName = scanner.nextLine();
			System.out.println("\nEnter device colour");
			deviceColour = scanner.nextLine();
			System.out.println("\nEnter device power");
			devicePower = scanner.nextLine();
			System.out.println("\nIs the device pluggedIn? (true/false)");
			pluggedIn = scanner.nextLine();
			System.out.println("\nIs the device male or female?");
			gender = scanner.nextLine();
			ElectricDevices beautyGadget = new BeautyGadgets(deviceName, deviceColour, Integer.parseInt(devicePower),
					Boolean.parseBoolean(pluggedIn), gender);
			devicesList.add(beautyGadget);
			break;
		case "2":
			System.out.println("\nEnter device name");
			deviceName = scanner.nextLine();
			System.out.println("\nEnter device colour");
			deviceColour = scanner.nextLine();
			System.out.println("\nEnter device power");
			devicePower = scanner.nextLine();
			System.out.println("\nIs the device pluggedIn? (true/false)");
			pluggedIn = scanner.nextLine();
			System.out.println("\nIs the device for heating or cooling?");
			specialisation = scanner.nextLine();
			ElectricDevices climatDevice = new BeautyGadgets(deviceName, deviceColour, Integer.parseInt(devicePower),
					Boolean.parseBoolean(pluggedIn), specialisation);
			devicesList.add(climatDevice);
			break;
		case "3":
			System.out.println("\nEnter device name");
			deviceName = scanner.nextLine();
			System.out.println("\nEnter device colour");
			deviceColour = scanner.nextLine();
			System.out.println("\nEnter device power");
			devicePower = scanner.nextLine();
			System.out.println("\nIs the device pluggedIn? (true/false)");
			pluggedIn = scanner.nextLine();
			System.out.println("\nIs the device for kitchen or bathoom?");
			room = scanner.nextLine();
			ElectricDevices householdAppliance = new BeautyGadgets(deviceName, deviceColour,
					Integer.parseInt(devicePower), Boolean.parseBoolean(pluggedIn), room);
			devicesList.add(householdAppliance);
			break;
		default:
			System.out.println("Specified device is not valid!");
		}

		System.out.println("\nWould you add one more device? Y/N");
		String answer = scanner.nextLine().toLowerCase();
		if (answer.equals("y")) {
			fillDevicesList(devicesList);
		} else {
		}
		return devicesList;
	}

	public static void sortDevicesListByName(ArrayList<ElectricDevices> devicesList) {
		System.out.println("\nObjects before sorting:");
		for (int i = 0; i < devicesList.size(); i++) {
			System.out.println(devicesList.get(i).getInfo());
		}

		System.out.println("\nObjects sorted by name:");
		Collections.sort(devicesList, new CollectHomeDevices());
		for (int i = 0; i < devicesList.size(); i++) {
			System.out.println(devicesList.get(i).getInfo());
		}
	}

	@Override
	public int compare(ElectricDevices d1, ElectricDevices d2) {
		String name1 = d1.getName();
		String name2 = d2.getName();
		return name1.compareTo(name2);
	}

	public static void calculatePower(ArrayList<ElectricDevices> devicesList) {
		int power = 0;
		for (int i = 0; i < devicesList.size(); i++) {
			boolean isPluggedIn = devicesList.get(i).getPluggedIn();
			power += devicesList.get(i).power(isPluggedIn);
		}
		System.out.println("\nTotal power of plugged in devices is:" + power);
	}

	public static void findByCriteria(ArrayList<ElectricDevices> devicesList) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter device colour");
		String colour = scanner.nextLine();
		System.out.println("\nIs the device plugged in (true/false)?");
		String isPluggedIn = scanner.nextLine();
		System.out.println("\nEnter min device power");
		String power = scanner.nextLine();

		ArrayList<ElectricDevices> correspDevices = new ArrayList<ElectricDevices>();

		for (int i = 0; i < devicesList.size(); i++) {

			if ((devicesList.get(i).getColour().equals(colour))
					&& (devicesList.get(i).getPluggedIn() == Boolean.parseBoolean(isPluggedIn))
					&& (devicesList.get(i).getDevicePower() >= Integer.parseInt(power))) {
				correspDevices.add(devicesList.get(i));
			}
		}
		if (devicesList.isEmpty()) {
			System.out.println("There are no devices that correspond your criteria!");
		} else {
			System.out.println("\nThe devices that corresponds creterias are:");
			for (int i = 0; i < correspDevices.size(); i++) {
				System.out.println(correspDevices.get(i).getInfo());
			}
		}
	}

}
