package epam.com.java.task1.tests;

import org.testng.annotations.BeforeClass;

import epam.java.task1.electricDevices.BeautyGadgets;

public class BeautyGadgetsBasicTest {
	
	protected BeautyGadgets beautyGadgets;
	protected String name;
	protected String colour;
	protected int devicePower;
	protected boolean pluggedIn;
	protected String gender;
	
	
	@BeforeClass
	public void setUp(){							
		beautyGadgets = new BeautyGadgets(name, colour, devicePower, pluggedIn, gender);
	}

}
