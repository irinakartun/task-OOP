package epam.com.java.task1.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BeautyGadgetTests extends BeautyGadgetsBasicTest{

	@Test(dataProvider = "dataProviderForSetPower")
    public void setPowerTest(boolean pluggedIn, int expResult){
		beautyGadgets.setDevicePower(45);
		beautyGadgets.setPluggedIn(pluggedIn);
        int result = beautyGadgets.power(pluggedIn);
        assertEquals(result, expResult, "Invalid result of operation!");
    }
	
	@DataProvider(name = "dataProviderForSetPower")
    public Object[][] dataProviderSetPower(){
        return new Object[][]{
                {true, 45},
                {false, 0}};
    }
	
	@Test(dataProvider = "dataProviderForSetGender")
    public void setGenderTest(String gender, String expResult){
		beautyGadgets.setGender(gender);
        String result = beautyGadgets.getGender();
        assertEquals(result, expResult, "Invalid result of operation!");
    }
	
	
	@DataProvider(name = "dataProviderForSetGender")
    public Object[][] dataProviderSetGender(){
        return new Object[][]{
                {"male", "male"},
                {"female", "female"},
                {"test#special%simbols", "test#special%simbols"},
                {"test spaces", "test spaces"}};
    }
	
	@Test
    public void getGenderTest(){
		beautyGadgets.setGender("male");
        String result = beautyGadgets.getGender();
        assertEquals(result, "male", "Invalid result of operation!");
    }
	
	@Test(dataProvider = "dataProviderForGetInfo")
    public void getInfoTest(String name, String colour, int power, boolean isPlugged, String gender, String expResult){
		beautyGadgets.setName(name);
		beautyGadgets.setColour(colour);
		beautyGadgets.setDevicePower(power);
		beautyGadgets.setPluggedIn(isPlugged);
		beautyGadgets.setGender(gender);
        String result = beautyGadgets.getInfo();
        assertEquals(result, expResult, "Invalid result of operation!");
    }
	
	@DataProvider(name = "dataProviderForGetInfo")
    public Object[][] dataProviderGetInfo(){
        return new Object[][]{
                {"razor", "black", 45, false, "male", "\n Name: razor" + "\n Colour: black" + "\n Device Power: 45" + "\n Is plugges in: false" + "\n Gender: male"},
                {"ra zor", "bl ack", 0, true, "fem ale", "\n Name: ra zor" + "\n Colour: bl ack" + "\n Device Power: 0" + "\n Is plugges in: true" + "\n Gender: fem ale"},
                {"ra%zor", "bl&ack", 8000, true, "fem#ale", "\n Name: ra%zor" + "\n Colour: bl&ack" + "\n Device Power: 8000" + "\n Is plugges in: true" + "\n Gender: fem#ale"}};
    }
	
	
	
}
