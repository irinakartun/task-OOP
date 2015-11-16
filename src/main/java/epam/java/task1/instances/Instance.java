package epam.java.task1.instances;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import epam.java.task1.electricDevices.CollectHomeDevices;

public interface Instance {

	public CollectHomeDevices readFromInstance() throws SQLException,ParserConfigurationException, SAXException, IOException;

	public void writeToInstance(CollectHomeDevices collectedDevices) throws SQLException, ParserConfigurationException, SAXException,
																			IOException, TransformerConfigurationException,	TransformerException;

}
