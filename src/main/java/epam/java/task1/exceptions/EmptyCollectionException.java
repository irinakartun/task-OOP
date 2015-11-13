package epam.java.task1.exceptions;

import java.util.ArrayList;

import epam.java.task1.electricDevices.ElectricDevices;

public class EmptyCollectionException extends Exception{

	public EmptyCollectionException(String errorMessage) {
		super(errorMessage);
	}


}
