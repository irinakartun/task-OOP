package epam.java.task1.running;

import java.util.ArrayList;
import java.util.Scanner;

import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.homeDevices.BeautyGadgets;

public class Running {

	public static void main(String[] args) {

		String deviceName;
		String deviceColour;
		String devicePower;
		String pluggedIn;
		String gender;
		String specialisation;
		String room;
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		CollectHomeDevices collectedDevices = new CollectHomeDevices();
		collectedDevices.setDevicesList(devicesList);

		Scanner scanner = new Scanner(System.in);
		System.out.println("\nWould you like to add new device?");
		String answer = scanner.nextLine().toLowerCase();

		while (answer.equals("y")) {
			System.out.println("\nWhich device would you like to create?");
			System.out.println("1" + "\nBeauty gadget\n" + "2"
					+ "\nClimat equipment\n" + "3" + "\nHousehold appliance\n");
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
				ElectricDevices beautyGadget = new BeautyGadgets(deviceName,
						deviceColour, Integer.parseInt(devicePower),
						Boolean.parseBoolean(pluggedIn), gender);
				collectedDevices.getDevicesList().add(beautyGadget);
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
				ElectricDevices climatDevice = new BeautyGadgets(deviceName,
						deviceColour, Integer.parseInt(devicePower),
						Boolean.parseBoolean(pluggedIn), specialisation);
				collectedDevices.getDevicesList().add(climatDevice);
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
				ElectricDevices householdAppliance = new BeautyGadgets(
						deviceName, deviceColour,
						Integer.parseInt(devicePower),
						Boolean.parseBoolean(pluggedIn), room);
				collectedDevices.getDevicesList().add(householdAppliance);
				break;
			default:
				System.out.println("Specified device is not valid!");
			}
			System.out.println("\nWould you like to add new device?");
			answer = scanner.nextLine().toLowerCase();
		}

		collectedDevices.sortDevicesListByName();
		collectedDevices.calculatePower();
		collectedDevices.findByCriteria();

	}

}
