package epam.java.task1.exceptions;

public class WrongPowerException extends Exception{
	
	private String power;

	public WrongPowerException(String power) {
		this.power = power;
	}

	public String getPower() {
		return power;
	}

}
