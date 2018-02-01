package xml;

public class XMLtester {
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		XMLreader xml_reader = new XMLreader();
		SimulationSetup simInfo = xml_reader.read("data/game_of_life.xml");
		simInfo.printInfo();
	}
}
