package epam.java.task1.exceptions;

public class WrongDeviceTypeException extends Exception {

	private String deviceType;

	public WrongDeviceTypeException(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceType() {
		return deviceType;
	}

}
