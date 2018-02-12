package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import grid.FireSimGrid;
import xml.XMLmaker;

/**
 * FireXMLmaker - creates fire XML files for use with project
 * @author marcusoertle
 *
 */
public class FireXMLmaker extends XMLmaker{
	private final static String FILE = "fire_saved";
	private final static String TYPE = "fire";
	private final static String FIRE_TITLE =  "Fire";
	private final static String FIRE_AUTHOR =  "Angela B. Shiflet";
	
	
	private double prob_fire = 0.5;
	private double prob_lightning = 0.01;
	private double prob_newTree = 0.01;
	
	public FireXMLmaker(String file, String shape, String nT, String eT, int gx, int gy, int cx, int cy, double probFire, double probLightning, double probNewTree) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, FIRE_TITLE, FIRE_AUTHOR, shape, nT, eT, gx, gy, cx, cy);
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
	
	/**
	 * Alternative inputs options for FireXMLmaker
	 * @param grid
	 * @param gx
	 * @param gy
	 * @param cx
	 * @param cy
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public FireXMLmaker(FireSimGrid grid, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, FIRE_TITLE, FIRE_AUTHOR);
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		shape = grid.getShape();
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly;
		neighbourType = grid.getNeighborArrangement();
		edgeType = grid.getEdgeType();
		printFileHeader2();
		printFireHeader();
		String[][] cellArray = grid.getArray();
		String type;
		for(int i = 0; i < cellArray[0].length; i++){
			for(int j = 0; j < cellArray[1].length; j++){
				type = cellArray[i][j];
				if(!type.equals("tree")){
					printCell(type, i, j);
				}
			}
		}
		closeWriter();
	}
	
	/**
	 * Prints the header for a fire simulation
	 */
	private void printFireHeader(){
		writer.println("\t" + "<fireProb>" + prob_fire + "</fireProb>");
		writer.println("\t" + "<lightningProb>" + prob_lightning + "</lightningProb>");
		writer.println("\t" + "<emptyTreeProb>" + prob_newTree + "</emptyTreeProb>");
		writer.println("");
	}
}
