package epam.java.task1.electricDevices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
		Collections.sort(this.devicesList, new CollectHomeDevices());
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
	}

	public ArrayList<ElectricDevices> findByCriteria(String[] criteria) {
		
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
	}

}
