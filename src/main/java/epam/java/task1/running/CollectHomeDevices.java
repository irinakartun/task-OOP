package epam.java.task1.running;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.exceptions.EmptyCollectionException;

public class CollectHomeDevices implements Comparator<ElectricDevices> {

	private ArrayList<ElectricDevices> devicesList;

	public CollectHomeDevices() {
	}

	public void setDevicesList(ArrayList<ElectricDevices> devicesList) {
		this.devicesList = devicesList;
	}

	public ArrayList<ElectricDevices> getDevicesList() {
		return devicesList;
	}

	public CollectHomeDevices sortDevicesListByName() {
		// System.out.println("\nObjects before sorting:");
		// for (int i = 0; i < this.devicesList.size(); i++) {
		// System.out.println(this.devicesList.get(i).getInfo());
		// }

		// System.out.println("\nObjects sorted by name:");
		Collections.sort(this.devicesList, new CollectHomeDevices());
		// for (int i = 0; i < this.devicesList.size(); i++) {
		// System.out.println(this.devicesList.get(i).getInfo());
		// }
		return this;
	}

	@Override
	public int compare(ElectricDevices d1, ElectricDevices d2) {
		String name1 = d1.getName();
		String name2 = d2.getName();
		return name1.compareTo(name2);
	}

	public int calculatePower() {
		int power = 0;
		for (int i = 0; i < this.devicesList.size(); i++) {
			boolean isPluggedIn = this.devicesList.get(i).getPluggedIn();
			power += this.devicesList.get(i).power(isPluggedIn);
		}
		return power;
		// System.out.println("\nTotal power of plugged in devices is:" +
		// power);
	}

	public ArrayList<ElectricDevices> findByCriteria(String[] criteria) {
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("\nEnter device colour");
		// String colour = scanner.nextLine();
		// System.out.println("\nIs the device plugged in (true/false)?");
		// String isPluggedIn = scanner.nextLine();
		// System.out.println("\nEnter min device power");
		// String power = scanner.nextLine();
		ArrayList<ElectricDevices> correspDevices = new ArrayList<ElectricDevices>();

		for (int i = 0; i < this.devicesList.size(); i++) {

			if ((this.devicesList.get(i).getColour().equals(criteria[0]))
					&& (this.devicesList.get(i).getPluggedIn() == Boolean
							.parseBoolean(criteria[1]))
					&& (this.devicesList.get(i).getDevicePower() >= Integer
							.parseInt(criteria[2]))) {
				correspDevices.add(this.devicesList.get(i));
			}
		}
		return correspDevices;
		// if (this.devicesList.isEmpty()) {
		// System.out.println("There are no devices that correspond your criteria!");
		// } else {
		// System.out.println("\nThe devices that corresponds creterias are:");
		// for (int i = 0; i < correspDevices.size(); i++) {
		// System.out.println(correspDevices.get(i).getInfo());
		// }
		// }
	}

}
