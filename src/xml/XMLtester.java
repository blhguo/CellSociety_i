package xml;

public class XMLtester {
	
	/**
	 * Start the program.
	 * Creates new XMLreader class and SimulationSetup class
	 * Reads XML file
	 * Prints results
	 */
	public static void main (String[] args) {
		//String fileName = "data/game_of_life.xml";
		String fileName = "data/segregation.xml";
		XMLreader xml_reader = new XMLreader();
		SimulationSetup simInfo = xml_reader.read(fileName);
		simInfo.printInfo();
	}
}
