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
import xml.SimSetups.FireSimSetup;

/**
 * @see XMLreader
 * Extend XMLreader for Fire simulation type
 */
public class FireXMLreader extends XMLreader{
	public FireSimSetup read(String filename) {
		try {
			SimulationSetup simSetup = super.read(filename);

			File file = new File(filename);
			DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dbF.newDocumentBuilder();
			Document doc = dB.parse(file);
			doc.getDocumentElement().normalize();	

			int numCellsX = (int) simSetup.getGridX() / simSetup.getCellX();
			int numCellsY = (int) simSetup.getGridY() / simSetup.getCellY();

			// Get unique simulation information
			NodeList fireProbList = doc.getElementsByTagName("fireProb");
			String fireProbString = getStringValue(fireProbList);
			double fireProb = Double.parseDouble(fireProbString);
			
			NodeList lightningProbList = doc.getElementsByTagName("lightningProb");
			String lightningProbString = getStringValue(lightningProbList);
			double lightningProb = Double.parseDouble(lightningProbString);
			
			NodeList emptyTreeProbList = doc.getElementsByTagName("emptyTreeProb");
			String emptyTreeProbString = getStringValue(emptyTreeProbList);
			double emptyTreeProb = Double.parseDouble(emptyTreeProbString);

			NodeList nList = doc.getElementsByTagName("cell");
			String[][] typeArray = new String[numCellsX][numCellsY];

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList typeList = eElement.getElementsByTagName("type");
					NodeList xList = eElement.getElementsByTagName("x");
					NodeList yList = eElement.getElementsByTagName("y");
					// get type
					String type = getStringValue(typeList);;

					// get x position
					String xString = getStringValue(xList);
					int x = Integer.parseInt(xString);

					// get y position
					String yString = getStringValue(yList);
					int y = Integer.parseInt(yString);

					// add to arrays
					typeArray[x][y] = type;
				}
			}
			typeArray = fillEmpty(typeArray);
			return new FireSimSetup(simSetup, typeArray, fireProb, lightningProb, emptyTreeProb);
		} catch (Exception e) {
			return null;
			//e.printStackTrace();
		}
	}
	
	/**
	 * @see XMLreader:fillEmpty(String[][] typeArray)
	 */
	@Override
	protected String[][] fillEmpty(String[][] typeArray) {
		String empty = "tree";
		for(int i = 0; i<typeArray[0].length; i++)
		{
			for(int j = 0; j<typeArray[1].length; j++)
			{
				if(typeArray[i][j] == null) {
					typeArray[i][j] = empty;
				}
			}
		}
		return typeArray;
	}
}
