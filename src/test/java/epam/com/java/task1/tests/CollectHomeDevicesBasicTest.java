package epam.com.java.task1.tests;

import org.testng.annotations.BeforeClass;

import epam.java.task1.electricDevices.CollectHomeDevices;


public class CollectHomeDevicesBasicTest {
	
	protected CollectHomeDevices ñollectHomeDevices;
	
	
	@BeforeClass
	public void setUp(){							
		ñollectHomeDevices = new CollectHomeDevices();
	}

}
