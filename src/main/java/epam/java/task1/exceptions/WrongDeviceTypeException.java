package epam.java.task1.exceptions;

public class WrongDeviceTypeException extends RuntimeException {

	public WrongDeviceTypeException(String errorMessage) {
		super(errorMessage);
	}

}
