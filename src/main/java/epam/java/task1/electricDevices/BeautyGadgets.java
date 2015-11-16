package epam.java.task1.electricDevices;


public class BeautyGadgets extends ElectricDevices {

	protected String gender; // for man or woman

	public BeautyGadgets(String name, String colour, int devicePower, boolean pluggedIn, String gender) { // constructor
		super(name, colour, devicePower, pluggedIn);
		this.gender = gender;
	}

	@Override
	public int power(boolean pluggedIn) { 	// interface method of power
											// calculation
		if (pluggedIn) {
			return devicePower; 			// for plugged in devices
		} else {
			return 0; 						// for not plugged in devices
		}
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public String getInfo() {
		return super.getInfo() + "\n Gender: " + this.getGender();
	}

}
