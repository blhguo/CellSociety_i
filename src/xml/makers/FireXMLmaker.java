package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import xml.XMLmaker;

public class FireXMLmaker extends XMLmaker{
	private final static String TYPE = "fire";
	private final static String FIRE_TITLE =  "Fire";
	private final static String FIRE_AUTHOR =  "Angela B. Shiflet";
	
	
	private double prob_fire = 0.5;
	private double prob_lightning = 0.01;
	private double prob_newTree = 0.01;
	
	public FireXMLmaker(String file, String shape, int gx, int gy, int cx, int cy, double probFire, double probLightning, double probNewTree) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, FIRE_TITLE, FIRE_AUTHOR, shape, gx, gy, cx, cy);
		prob_fire = probFire;
		prob_lightning = probLightning;
		prob_newTree = probNewTree;
		printFireHeader();
		String empty = "empty";
		for(int i = 0; i < numCellsX; i++){
			printCell(empty, i, 0);
			printCell(empty, i, numCellsY-1);
		}

		for(int i = 0; i < numCellsY; i++){
			printCell(empty, 0, i);
			printCell(empty, numCellsX-1, i);
		}

		int centerX = (int) (numCellsX-1)/2;
		int centerY = (int) (numCellsY-1)/2;
		printCell("fire", centerX, centerY);
		closeWriter();
	}
	
	private void printFireHeader(){
		writer.println("\t" + "<fireProb>" + prob_fire + "</fireProb>");
		writer.println("\t" + "<lightningProb>" + prob_lightning + "</lightningProb>");
		writer.println("\t" + "<emptyTreeProb>" + prob_newTree + "</emptyTreeProb>");
		writer.println("");
	}
}
