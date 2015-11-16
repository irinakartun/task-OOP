package epam.java.task1.electricDevices;


public class HouseholdAppliances extends ElectricDevices {

	private String room; // for kitchen or bathroom

	public HouseholdAppliances(String name, String colour, int devicePower, boolean pluggedIn, String room) { // constructor
		super(name, colour, devicePower, pluggedIn);
		this.room = room;
	}

	@Override
	public int power(boolean pluggedIn) { // interface method of power
											// calculation
		if (pluggedIn) {
			return devicePower; // for plugged in devices
		} else {
			return 0; // for not plugged in devices
		}
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoom() {
		return room;
	}
	
	public String getInfo() {
		return super.getInfo() + "\n Room: " + this.getRoom();
	}

}
