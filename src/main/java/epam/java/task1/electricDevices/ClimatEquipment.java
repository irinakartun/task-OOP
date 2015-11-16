package epam.java.task1.electricDevices;


public class ClimatEquipment extends ElectricDevices {

	protected String specialisation; // for man or woman

	public ClimatEquipment(String name, String colour, int devicePower, boolean pluggedIn, String specialisation) { // constructor
		super(name, colour, devicePower, pluggedIn);
		this.specialisation = specialisation;
	}

	@Override
	public int power(boolean pluggedIn) { 		// interface method of power calculation
		if (pluggedIn) {
			return devicePower; 				// for plugged in devices
		} else {
			return 0; 							// for not plugged in devices
		}
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getSpecialisation() {
		return specialisation;
	}
	
	public String getInfo() {
		return super.getInfo() + "\n Specialisation: " + this.getSpecialisation();
	}

}
