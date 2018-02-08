package xml.readers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.SimulationSetup;
import xml.XMLreader;
import xml.SimSetups.SegregationSimSetup;

/**
 * @see XMLreader
 * Extends XMLreader for Segregation simulation type
 */
public class SegregationXMLreader extends XMLreader {
	public SegregationSimSetup read(String filename) {
		try {
			SimulationSetup simSetup = super.read(filename);
			
			File file = new File(filename);
			DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dbF.newDocumentBuilder();
			Document doc = dB.parse(file);
			doc.getDocumentElement().normalize();	

			int numCellsX = (int) simSetup.getGridX() / simSetup.getCellX();
			int numCellsY = (int) simSetup.getGridY() / simSetup.getCellY();
			
			NodeList nList = doc.getElementsByTagName("cell");
			String[][] typeArray = new String[numCellsX][numCellsY];
			double[][] thresholdArray = new double[numCellsX][numCellsY];

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList typeList = eElement.getElementsByTagName("type");
					NodeList xList = eElement.getElementsByTagName("x");
					NodeList yList = eElement.getElementsByTagName("y");
					NodeList thresholdList = eElement.getElementsByTagName("threshold");

					// get type
					String type = getStringValue(typeList);;

					// get x position
					String xString = getStringValue(xList);
					int x = Integer.parseInt(xString);

					// get y position
					String yString = getStringValue(yList);
					int y = Integer.parseInt(yString);

					// add type to array
					typeArray[x][y] = type;
					
					String thresholdString = getStringValue(thresholdList);
					double threshold = Double.parseDouble(thresholdString);
					thresholdArray[x][y] = threshold;
				}
			}
			typeArray = fillEmpty(typeArray);
			return new SegregationSimSetup(simSetup, typeArray, thresholdArray);
		} catch (Exception e) {
			//e.printStackTrace();
		}	
		return null;
	}
}
