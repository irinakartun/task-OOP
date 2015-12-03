package epam.com.java.task1.tests;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;

public class ÑollectHomeDevicesTests extends CollectHomeDevicesBasicTest{

	
	@Test(dataProvider = "dataProviderForSortDevices")
    public void sortDevicesByName(String name1, String name2, String name3){
		
		BeautyGadgets device1 = new BeautyGadgets(name1, "black", 30, false, "male");
		ClimatEquipment device2 = new ClimatEquipment(name2, "white", 500, true, "cooling");
		HouseholdAppliances device3 = new HouseholdAppliances(name3, "green", 95, true, "kitchen");
		
		ArrayList<ElectricDevices> results = new ArrayList<ElectricDevices>();
		results.add(device1);
		results.add(device2);
		results.add(device3);
		ñollectHomeDevices.setDevicesList(results);
		
		String[] expArray = {name1, name2, name3};
		Arrays.sort(expArray);
		
		for (int i = 0; i < results.size(); i++){
			assertTrue((ñollectHomeDevices.sortDevicesListByName().getDevicesList().get(i).getName()).equals(expArray[i]), "The sorting is wrong!");	
		}
		
    }
	
	@DataProvider(name = "dataProviderForSortDevices")
    public Object[][] dataProviderSortDevices(){
        return new Object[][]{
                {"kettle", "air", "oven"},
                {"clarisonic", "tv", "heating"},
                {"123", "tv", "heating"},
                {"clarisonic", " tv", "heating"},
                {"clarisonic", "tv", "$heating"}};
    }
	
	
	
	@Test(dataProvider = "dataProviderForCalculatePower")
    public void calculatePowerTest(int power1, int power2, int power3, int expResult){
		
		BeautyGadgets device1 = new BeautyGadgets("razor", "black", power1, false, "male");
		ClimatEquipment device2 = new ClimatEquipment("air condition", "white", power2, true, "cooling");
		HouseholdAppliances device3 = new HouseholdAppliances("kettle", "green", power3, true, "kitchen");
		
		ArrayList<ElectricDevices> results = new ArrayList<ElectricDevices>();
		results.add(device1);
		results.add(device2);
		results.add(device3);
		ñollectHomeDevices.setDevicesList(results);
		
		int result = ñollectHomeDevices.calculatePower();
		
		assertEquals(result, expResult, "Invalid result of function!");
    }
	
	@DataProvider(name = "dataProviderForCalculatePower")
    public Object[][] dataProviderCalculatePower(){
        return new Object[][]{
                {25, 500, 50, 550},
                {60, 0, 80, 80},
                {20, 500, -30, 470}};
    }
	
	@Test(dataProvider = "dataProviderForFindByCriteria")
    public void findByCriteriaTest(String colour, String pluggedIn, String power, ArrayList<ElectricDevices> expResult){
		
		String[] criteria = {colour, pluggedIn,power};
		
		BeautyGadgets device1 = new BeautyGadgets("razor", "black", 30, false, "male");
		ClimatEquipment device2 = new ClimatEquipment("air condition", "white", 500, true, "cooling");
		HouseholdAppliances device3 = new HouseholdAppliances("kettle", "green", 95, true, "kitchen");
		HouseholdAppliances device4 = new HouseholdAppliances("toaster", "white", 100, true, "kitchen");
		
		ArrayList<ElectricDevices> results = new ArrayList<ElectricDevices>();
		results.add(device1);
		results.add(device2);
		results.add(device3);
		results.add(device4);
		ñollectHomeDevices.setDevicesList(results);
		
		for (int i = 0; i < ñollectHomeDevices.findByCriteria(criteria).size(); i++){
			assertTrue((ñollectHomeDevices.findByCriteria(criteria).get(i).getName()).equals(expResult.get(i).getName()), "Invalid result of operation!");
		}
    }
	
	@DataProvider(name = "dataProviderForFindByCriteria")
    public Object[][] dataProviderFindByCriteria(){
		
		ArrayList<ElectricDevices> expList1 = new ArrayList<ElectricDevices>();
		expList1.add(new BeautyGadgets("razor", "black", 30, false, "male"));
		
		ArrayList<ElectricDevices> expList2 = new ArrayList<ElectricDevices>();
		
		ArrayList<ElectricDevices> expList3 = new ArrayList<ElectricDevices>();
		expList3.add(new BeautyGadgets("air condition", "white", 500, true, "cooling"));
		expList3.add(new BeautyGadgets("toaster", "white", 100, true, "kitchen"));
		
        return new Object[][]{
                {"black", "false", "10", expList1},
                {"red", "true", "80", expList2},
                {"white", "true", "70", expList3}};
    }
		
}
