package epam.java.task1.electricDevices;

public abstract class ElectricDevices {

	protected String name;
	protected String colour;
	protected int devicePower;
	protected boolean pluggedIn;

	public ElectricDevices(String name, String colour, int devicePower, boolean pluggedIn) {
		this.name = name;
		this.colour = colour;
		this.devicePower = devicePower;
		this.pluggedIn = pluggedIn;
	}

	abstract public int power(boolean pluggedIn); 	// abstract method for power calculation

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public void setDevicePower(int devicePower) {
		this.devicePower = devicePower;
	}

	public int getDevicePower() {
		return devicePower;
	}
	
	public void setPluggedIn(boolean pluggedIn) {
		this.pluggedIn = pluggedIn;
	}

	public boolean getPluggedIn() {
		return pluggedIn;
	}
	
	public String getInfo() {
		return "\n Name: " + this.getName() + "\n Colour: " + this.getColour() + "\n Device Power: "
				+ this.getDevicePower() + "\n Is plugges in: " + this.getPluggedIn();
	}

}
