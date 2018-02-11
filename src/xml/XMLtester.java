package xml;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import xml.SimSetups.*;
import xml.makers.*;
import xml.readers.*;

public class XMLtester {
	private final static String GOL = "data/game_of_life.xml";
	private final static String SEGREGATION = "data/segregation.xml";
	private final static String WATOR = "data/wator.xml";
	private final static String FIRE =  "data/fire.xml";
	
	/**
	 * Start the program.
	 * Used for testing XML files
	 * Creates new XMLreader class and SimulationSetup class
	 * Reads XML file
	 * Prints results
	 * 
	 * Use: change the first four lines to the type of simulation you are testing the XML for
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String fileName = GOL;
		//String fileName = SEGREGATION;
		//String fileName = WATOR;
		//String fileName = FIRE;
		
		if(fileName.equals(SEGREGATION)) {
			SegregationXMLreader xml_reader = new SegregationXMLreader();
			SegregationSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
			new SegXMLmaker("seg_test", "square", 400, 400, 20, 20, 0.3, 0.3, 0.3);
		}
		else if(fileName.equals(WATOR)) {
			WatorXMLreader xml_reader = new WatorXMLreader();
			WatorSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
			new WatorXMLmaker("wator_test", "square", 400, 400, 10, 10, 0.5, 0.3, 10, 5, 5, 4);
		}
		else if(fileName.equals(FIRE)) {
			FireXMLreader xml_reader = new FireXMLreader();
			FireSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
			new FireXMLmaker("fire_test", "square", 400, 400, 10, 10, 0.5, 0.01, 0.01);
		}
		else {
			GOLXMLreader xml_reader = new GOLXMLreader();
			GOLSimSetup simInfo = xml_reader.read(fileName);
			simInfo.printInfo();
			new GOLXMLmaker("gol_test", "square", 400, 400, 40, 40, 0.3);
		}
		
	}
}
