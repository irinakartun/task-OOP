package epam.java.task1.running;

import java.util.ArrayList;
import epam.java.task1.electricDevices.ElectricDevices;


public class Running {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ElectricDevices> houseDevices = new ArrayList<ElectricDevices>();
		
		CollectHomeDevices.fillDevicesList(houseDevices);
		CollectHomeDevices.sortDevicesListByName(houseDevices);
		CollectHomeDevices.calculatePower(houseDevices);
		CollectHomeDevices.findByCriteria(houseDevices);
	}

}
