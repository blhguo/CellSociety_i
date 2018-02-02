package xml;

import xml.SimSetups.*;
import xml.readers.*;

public class XMLtester {
	private final static String gol = "data/game_of_life.xml";
	private final static String segregation = "data/segregation.xml";
	private final static String wator = "data/wator.xml";
	private final static String fire =  "data/fire.xml";
	
	/**
	 * Start the program.
	 * Creates new XMLreader class and SimulationSetup class
	 * Reads XML file
	 * Prints results
	 */
	public static void main (String[] args) {
		//String fileName = gol;
		//String fileName = segregation;
		//String fileName = wator;
		String fileName = fire;
		
		if(fileName.equals(segregation)) {
			SegregationXMLreader xml_reader = new SegregationXMLreader();
			SegregationSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
		}
		else if(fileName.equals(wator)) {
			WatorXMLreader xml_reader = new WatorXMLreader();
			WatorSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
		}
		else if(fileName.equals(fire)) {
			FireXMLreader xml_reader = new FireXMLreader();
			FireSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
		}
		else {
			GOLXMLreader xml_reader = new GOLXMLreader();
			GOLSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
		}
		
	}
}
