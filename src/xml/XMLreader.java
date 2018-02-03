package xml;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * XML Reader
 * @author Marcus Oertle
 * 
 * input:
 * @param filename (the name of the file to be read)
 * returns:
 * a new SimulationSetup class
 * 
 * Use:
 * Parses XML file for:
 * name
 * title
 * author
 * cell shape
 * cell x and y size
 * grid x and y size
 * list of all types of cells and their locations
 * 
 * Based on:
 * XML tutorial at:
 * https://www.tutorialspoint.com/java_xml/java_dom_query_document.htm
 */

public class XMLreader {
	public SimulationSetup read(String filename) {

		try {
			// Initial setup
			File file = new File(filename);
			DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dbF.newDocumentBuilder();
			Document doc = dB.parse(file);
			doc.getDocumentElement().normalize();

			// Get simulation information
			NodeList nameList = doc.getElementsByTagName("name");
			String name = getStringValue(nameList);

			NodeList titleList = doc.getElementsByTagName("title");
			String title = getStringValue(titleList);

			NodeList authorList = doc.getElementsByTagName("author");
			String author = getStringValue(authorList);

			NodeList shapeList = doc.getElementsByTagName("cell_shape");
			String cell_shape = getStringValue(shapeList);

			NodeList cell_xsizeList = doc.getElementsByTagName("cell_xsize");
			String cell_xsizeString = getStringValue(cell_xsizeList);
			int cell_xsize = Integer.parseInt(cell_xsizeString);

			NodeList cell_ysizeList = doc.getElementsByTagName("cell_ysize");
			String cell_ysizeString = getStringValue(cell_ysizeList);
			int cell_ysize = Integer.parseInt(cell_ysizeString);

			NodeList grid_xList = doc.getElementsByTagName("grid_x");
			String grid_xString = getStringValue(grid_xList);
			int grid_x = Integer.parseInt(grid_xString);

			NodeList grid_yList = doc.getElementsByTagName("grid_y");
			String grid_yString = getStringValue(grid_yList);
			int grid_y = Integer.parseInt(grid_yString);
			
			return new SimulationSetup(name, title, author, cell_shape, cell_xsize, 
					cell_ysize, grid_x, grid_y);
		} catch (Exception e) {
			return null;
		}	
	}


	/**
	 * Fills empty array slots with "e" for "empty"
	 * Used by:
	 * read()
	 * inputs: 
	 * @param typeArray (double array of types)
	 * returns:
	 * @return typeArray (the modified double array of types)
	 */
	protected String[][] fillEmpty(String[][] typeArray) {
		String empty = "e";
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


	/**
	 * Creates string from NodeList (assumes single length NodeLists)
	 * Used by:
	 * read()
	 * inputs: 
	 * @param list (NodeList of 1 value containing the string to parse out)
	 * returns:
	 * @return s (the parsed out string)
	 */
	protected String getStringValue(NodeList list) {
		Node node1 = list.item(0);
		Element e = (Element) node1;
		String s = e.getTextContent();
		return s;
	}
}