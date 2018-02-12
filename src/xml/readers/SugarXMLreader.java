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
import xml.SimSetups.SugarSimSetup;

/**
 * @see XMLreader
 * Extends XMLreader for sugar simulation type
 */
public class SugarXMLreader extends XMLreader {
	public SugarSimSetup read(String filename) {
		try {
			SimulationSetup simSetup = super.read(filename);

			File file = new File(filename);
			DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dbF.newDocumentBuilder();
			Document doc = dB.parse(file);
			doc.getDocumentElement().normalize();	

			int numCellsX = (int) simSetup.getGridX() / simSetup.getCellX();
			int numCellsY = (int) simSetup.getGridY() / simSetup.getCellY();

			NodeList agentList = doc.getElementsByTagName("agent");
			NodeList patchList = doc.getElementsByTagName("patch");

			int[][] patchSugarArray = new int[numCellsX][numCellsY];
			int[][] patchMaxSugarArray = new int[numCellsX][numCellsY];
			int[][] tickArray = new int[numCellsX][numCellsY];
			String[][] agentArray = new String[numCellsX][numCellsY];
			int[][] agentSugarArray = new int[numCellsX][numCellsY];
			int[][] agentMetabolismArray = new int[numCellsX][numCellsY];
			int[][] agentVisionArray = new int[numCellsX][numCellsY];

			// get all agents
			for (int temp = 0; temp < agentList.getLength(); temp++) {
				Node nNode = agentList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList sugarList = eElement.getElementsByTagName("sugar");
					NodeList sugarMetabolismList = eElement.getElementsByTagName("sugarMetabolism");
					NodeList visionList = eElement.getElementsByTagName("vision");
					NodeList xList = eElement.getElementsByTagName("x");
					NodeList yList = eElement.getElementsByTagName("y");

					// get x position
					String xString = getStringValue(xList);
					int x = Integer.parseInt(xString);

					// get y position
					String yString = getStringValue(yList);
					int y = Integer.parseInt(yString);

					// get sugar
					String sugarString = getStringValue(sugarList);
					int sugar = Integer.parseInt(sugarString);

					// get metabolism
					String sugarMetabolismString = getStringValue(sugarMetabolismList);
					int sugarMetabolism = Integer.parseInt(sugarMetabolismString);

					// get vision
					String visionString = getStringValue(visionList);
					int vision = Integer.parseInt(visionString);

					// add stuff to arrays
					agentSugarArray[x][y] = sugar;
					agentMetabolismArray[x][y] = sugarMetabolism;
					agentVisionArray[x][y] = vision;
					agentArray[x][y] = "agent";
				}
			}

			// get all patches
			for (int temp = 0; temp < patchList.getLength(); temp++) {
				Node nNode = patchList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList sugarList = eElement.getElementsByTagName("sugar");
					NodeList sugarMaxList = eElement.getElementsByTagName("sugarMax");
					NodeList tickList = eElement.getElementsByTagName("tick");
					NodeList xList = eElement.getElementsByTagName("x");
					NodeList yList = eElement.getElementsByTagName("y");

					// get x position
					String xString = getStringValue(xList);
					int x = Integer.parseInt(xString);

					// get y position
					String yString = getStringValue(yList);
					int y = Integer.parseInt(yString);

					// get sugar
					String sugarString = getStringValue(sugarList);
					int sugar = Integer.parseInt(sugarString);

					// get sugarMax
					String sugarMaxString = getStringValue(sugarMaxList);
					int sugarMax = Integer.parseInt(sugarMaxString);

					// get tick
					String tickString = getStringValue(tickList);
					int tick = Integer.parseInt(tickString);
					
					// add stuff to arrays
					patchSugarArray[x][y] = sugar;
					patchMaxSugarArray[x][y] = sugarMax;
					tickArray[x][y] = tick;
				}
			}
			agentArray = fillEmpty(agentArray);
			return new SugarSimSetup(simSetup, patchSugarArray, patchMaxSugarArray, tickArray,
					agentArray, agentSugarArray, agentMetabolismArray, agentVisionArray);
		} catch (Exception e) {
			//e.printStackTrace();
		}	
		return null;
	}
}
