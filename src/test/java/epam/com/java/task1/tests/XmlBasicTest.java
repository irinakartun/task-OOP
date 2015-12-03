package epam.com.java.task1.tests;

import org.testng.annotations.BeforeClass;

import epam.java.task1.instances.Xml;

public class XmlBasicTest {
	
	protected Xml xmlFile;
	protected String xmlInputPath;
	protected String xmlOutputPath;
	
	
	@BeforeClass
	public void setUp(){							
		xmlFile = new Xml(xmlInputPath, xmlOutputPath);
	}

}
