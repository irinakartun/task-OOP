package epam.java.task1.exceptions;

public class WrongPluggedInValue extends RuntimeException {
	
	public WrongPluggedInValue(String errorMessage) {
		super(errorMessage);
	}

}
