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
import xml.SimSetups.WatorSimSetup;

/**
 * @see XMLreader
 * Extends XMLreader for Wator simulation type
 */
public class WatorXMLreader extends XMLreader{
	public WatorSimSetup read(String filename) {
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
			int[][] reproductionArray = new int[numCellsX][numCellsY];
			int[][] gainedEnergyArray = new int[numCellsX][numCellsY];
			int[][] energyArray = new int[numCellsX][numCellsY];
			int[][] currentEnergyArray = new int[numCellsX][numCellsY];
			int[][] currentReproductionArray = new int[numCellsX][numCellsY];

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList typeList = eElement.getElementsByTagName("type");
					NodeList xList = eElement.getElementsByTagName("x");
					NodeList yList = eElement.getElementsByTagName("y");
					NodeList reproductionList = eElement.getElementsByTagName("reproductionThreshold");
					NodeList gainedEnergyList = eElement.getElementsByTagName("gainedEnergy");
					NodeList energyList = eElement.getElementsByTagName("energy");
					NodeList currentEnergyList = eElement.getElementsByTagName("currentEnergy");
					NodeList currentReproductionList = eElement.getElementsByTagName("currentReproduction");

					// get type
					String type = getStringValue(typeList);;

					// get x position
					String xString = getStringValue(xList);
					int x = Integer.parseInt(xString);

					// get y position
					String yString = getStringValue(yList);
					int y = Integer.parseInt(yString);

					// get reproductionThreshold
					String reproductionThresholdString = getStringValue(reproductionList);
					int reproductionThreshold = Integer.parseInt(reproductionThresholdString);
					
					// get gainedEnergy
					String gainedEnergyString = getStringValue(gainedEnergyList);
					int gainedEnergy = Integer.parseInt(gainedEnergyString);
					
					// get energy
					String energyString = getStringValue(energyList);
					int energy = Integer.parseInt(energyString);
					
					// get current energy
					String currentEnergyString = getStringValue(currentEnergyList);
					int currentEnergy = Integer.parseInt(currentEnergyString);
					
					// get current reproduction
					String currentReproductionString = getStringValue(currentReproductionList);
					int currentReproduction = Integer.parseInt(currentReproductionString);
					
					// add to arrays
					typeArray[x][y] = type;
					reproductionArray[x][y] = reproductionThreshold;
					gainedEnergyArray[x][y] = gainedEnergy;
					energyArray[x][y] = energy;
					currentEnergyArray[x][y] = currentEnergy;
					currentReproductionArray[x][y] = currentReproduction;
				}
			}
			typeArray = fillEmpty(typeArray);
			return new WatorSimSetup(simSetup, typeArray, reproductionArray, gainedEnergyArray, energyArray, currentEnergyArray, currentReproductionArray);
		} catch (Exception e) {
			//e.printStackTrace();
		}	
		return null;
	}
}
