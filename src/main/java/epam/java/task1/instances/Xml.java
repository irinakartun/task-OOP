package epam.java.task1.instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import epam.java.task1.electricDevices.BeautyGadgets;
import epam.java.task1.electricDevices.ClimatEquipment;
import epam.java.task1.electricDevices.CollectHomeDevices;
import epam.java.task1.electricDevices.ElectricDevices;
import epam.java.task1.electricDevices.HouseholdAppliances;

public class Xml implements Instance{
	
	public String xmlInputPath;
	public String xmlOutputPath;

	public Xml(String xmlInputPath, String xmlOutputPath){
		this.xmlInputPath = xmlInputPath;
		this.xmlOutputPath = xmlOutputPath;
	}
	
	@Override
	public CollectHomeDevices readFromInstance() throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		ArrayList<ElectricDevices> devicesList = new ArrayList<ElectricDevices>();
		CollectHomeDevices collectedDevices = new CollectHomeDevices();
		collectedDevices.setDevicesList(devicesList);
		
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(xmlInputPath));
			document.normalizeDocument();
			Element root = document.getDocumentElement();
			NodeList devices = root.getElementsByTagName("device");
	
			for (int i = 0; i < devices.getLength(); i++) {
				Element item = (Element) devices.item(i);
				String type = item.getAttribute("type");
				Element name = (Element) item.getElementsByTagName("name").item(0);
				Element colour = (Element) item.getElementsByTagName("colour").item(0);
				Element power = (Element) item.getElementsByTagName("power").item(0);
				Element pluggedIn = (Element) item.getElementsByTagName("pluggedin").item(0);
				Element attribute = (Element) item.getElementsByTagName("attribute").item(0);
				switch (type) {
				case "beauty gadget":
					ElectricDevices bDevice = new BeautyGadgets(
							name.getTextContent(), colour.getTextContent(),
							Integer.parseInt(power.getTextContent()),
							Boolean.parseBoolean(pluggedIn.getTextContent()),
							attribute.getTextContent());
					collectedDevices.getDevicesList().add(bDevice);
					break;
				case "climat equipment":
					ElectricDevices cDevice = new ClimatEquipment(
							name.getTextContent(), colour.getTextContent(),
							Integer.parseInt(power.getTextContent()),
							Boolean.parseBoolean(pluggedIn.getTextContent()),
							attribute.getTextContent());
					collectedDevices.getDevicesList().add(cDevice);
					break;
				case "household appliance":
					ElectricDevices hDevice = new HouseholdAppliances(
							name.getTextContent(), colour.getTextContent(),
							Integer.parseInt(power.getTextContent()),
							Boolean.parseBoolean(pluggedIn.getTextContent()),
							attribute.getTextContent());
					collectedDevices.getDevicesList().add(hDevice);
					break;
				default:
					System.out.println("Specified device type does not exist!");
				}
			}
		}
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		}
		catch (SAXException sxe){
			sxe.printStackTrace();
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
		return collectedDevices;
	}

	
	@Override
	public void writeToInstance(CollectHomeDevices collectedDevices)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = document.createElement("devices");
			document.appendChild(root);
			
			for (int i = 0; i < collectedDevices.getDevicesList().size(); i++){
				Element device = document.createElement("device");
				root.appendChild(device);
				
				Attr attr = document.createAttribute("type");
				attr.setValue(collectedDevices.getDevicesList().get(i).getClass().getSimpleName());
				device.setAttributeNode(attr);
				
				Element name = document.createElement("name");
				name.appendChild(document.createTextNode(collectedDevices.getDevicesList().get(i).getName()));
				device.appendChild(name);
				
				Element colour = document.createElement("colour");
				colour.appendChild(document.createTextNode(collectedDevices.getDevicesList().get(i).getColour()));
				device.appendChild(colour);
				
				Element power = document.createElement("power");
				power.appendChild(document.createTextNode(String.valueOf(collectedDevices.getDevicesList().get(i).getDevicePower())));
				device.appendChild(power);
				
				Element pluggedIn = document.createElement("pluggedin");
				pluggedIn.appendChild(document.createTextNode(String.valueOf(collectedDevices.getDevicesList().get(i).getPluggedIn())));
				device.appendChild(pluggedIn);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(xmlOutputPath));
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		}
		catch (TransformerException tfe){
			tfe.printStackTrace();
		}
	}
		
	
}
