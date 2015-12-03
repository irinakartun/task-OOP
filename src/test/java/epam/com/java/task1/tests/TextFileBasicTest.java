package epam.com.java.task1.tests;

import org.testng.annotations.BeforeClass;

import epam.java.task1.instances.TextFile;

public class TextFileBasicTest {
	
	protected TextFile textFile;
	protected String inputFile;
	protected String outputFile;
	
	
	@BeforeClass
	public void setUp(){							
		textFile = new TextFile(inputFile, outputFile);
	}

}
