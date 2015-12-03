package epam.com.java.task1.tests;

import org.testng.annotations.BeforeClass;

import epam.java.task1.instances.Database;

public class DatabaseBasicTest {
	
	protected Database database;
	protected String connectionString;
	
	
	@BeforeClass
	public void setUp(){							
		database = new Database(connectionString);
	}

}
