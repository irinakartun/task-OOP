package epam.java.task1.exceptions;

public class WrongPluggedInValue extends Exception{
	
	private String pluggedIn;

	public WrongPluggedInValue(String pluggedIn) {
		this.pluggedIn = pluggedIn;
	}

	public String getIsPluggedIn() {
		return pluggedIn;
	}

}
