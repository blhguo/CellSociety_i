package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import grid.GOLSimGrid;
import grid.SegregationSimGrid;
import grid.SugarSimGrid;
import xml.XMLmaker;

/**
 * SugarXMLmaker - creates sugar XML files for use with project
 * @author marcusoertle
 *
 */

public class SugarXMLmaker extends XMLmaker{
	private final static String FILE = "sugar_saved";
	private final static String TYPE = "sugar";
	private final static String SUGAR_TITLE = "Sugar Scape";
	private final static String SUGAR_AUTHOR = "Joshua M. Epstein and Robert Axtell";

	// sugar specific
	private double agentProb = 0.05;

	public SugarXMLmaker(String file, String shape, String nT, String eT, int gx, int gy, int cx, int cy, double aP) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, SUGAR_TITLE, SUGAR_AUTHOR, shape, nT, eT, gx, gy, cx, cy);
		agentProb = aP;
		printSugarHeader(1, 1);
		double agentNum = PROB * agentProb;
		for(int i = 0; i < numCellsX; i++){
			for(int j = 0; j < numCellsY; j++){
				Random rand = new Random();
				int n = rand.nextInt(PROB+1);
				Random randSugar = new Random();
				int sugar = randSugar.nextInt(21) + 5;
				Random randMetabolism = new Random();
				int metabolism = randMetabolism.nextInt(6) + 1;
				int vision = 4;

				if(n < agentNum){
					printSugarAgent(i,j, sugar, metabolism, vision);
				}
				else{
					continue;
				}
			}
		}
		for(int i = 0; i < numCellsX; i++){
			for(int j = 0; j < numCellsY; j++){
				if(j < numCellsY/5){
					printSugarPatch(i,j,4,4,0);
				}
				else if(j < (numCellsY/5 * 2)){
					printSugarPatch(i,j,3,3,0);
				}
				else if(j < (numCellsY/5 * 3)){
					printSugarPatch(i,j,2,2,0);
				}
				else if(j < (numCellsY/5 * 4)){
					printSugarPatch(i,j,1,1,0);
				}
				else{
					printSugarPatch(i,j,0,0,0);
				}
			}
		}
		closeWriter();
	}

	public SugarXMLmaker(SugarSimGrid grid, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, SUGAR_TITLE, SUGAR_AUTHOR);
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		shape = grid.getShape();
		neighbourType = grid.getNeighborArrangement();
		edgeType = grid.getEdgeType();
		int[][] patchSugarArray = grid.getPatchSugarArray();
		int[][] patchMaxSugarArray = grid.getPatchMaxSugarArray();
		int[][] patchTickArray = grid.getPatchTickArray();
		String[][] agentArray = grid.getArray();
		int[][] agentSugarArray = grid.getAgentSugarArray();
		int[][] agentMetabolismArray = grid.getAgentMetabolismArray();
		int[][] agentVisionArray = grid.getAgentVisionArray();
		int growRate = grid.getGrowRate();
		int growInterval = grid.getGrowInterval();
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly; 
		printFileHeader2();
		printSugarHeader(growInterval, growRate);
		String type;
		int agentSugar;
		int agentMetabolism;
		int agentVision;
		for(int i = 0; i < agentArray[0].length; i++){
			for(int j = 0; j < agentArray[1].length; j++){
				type = agentArray[i][j];
				agentSugar = agentSugarArray[i][j];
				agentMetabolism = agentMetabolismArray[i][j];
				agentVision = agentVisionArray[i][j];
				if(!type.equals("empty")){
					printSugarAgent(i, j, agentSugar, agentMetabolism, agentVision);
				}
			}
		}
		int patchSugar;
		int patchMaxSugar;
		int patchTick;
		for(int i = 0; i < patchSugarArray[0].length; i++){
			for(int j = 0; j < patchSugarArray[1].length; j++){
				patchSugar = patchSugarArray[i][j];
				patchMaxSugar = patchMaxSugarArray[i][j];
				patchTick = patchTickArray[i][j];
				printSugarPatch(i, j, patchSugar, patchMaxSugar, patchTick);
			}
		}
		closeWriter();
	}

	private void printSugarAgent(int i, int j, int agentSugar, int sugarMetabolism, int vision){
		writer.println("\t" + "<agent>");
		writer.println("\t" + "\t" + "<x>" + i + "</x>");
		writer.println("\t" + "\t" + "<y>" + j + "</y>");
		writer.println("\t" + "\t" + "<sugar>" + agentSugar + "</sugar>");
		writer.println("\t" + "\t" + "<sugarMetabolism>" + sugarMetabolism + "</sugarMetabolism>");
		writer.println("\t" + "\t" + "<vision>" + vision + "</vision>");
		writer.println("\t" + "</agent>");
		writer.println("");
	}
	
	private void printSugarPatch(int i, int j, int patchSugar, int sugarMax, int tick){
		writer.println("\t" + "<patch>");
		writer.println("\t" + "\t" + "<x>" + i + "</x>");
		writer.println("\t" + "\t" + "<y>" + j + "</y>");
		writer.println("\t" + "\t" + "<sugar>" + patchSugar + "</sugar>");
		writer.println("\t" + "\t" + "<sugarMax>" + sugarMax + "</sugarMax>");
		writer.println("\t" + "\t" + "<tick>" + tick + "</tick>");
		writer.println("\t" + "</patch>");
		writer.println("");
	}
	
	/**
	 * Prints the header for a sugar simulation
	 */
	private void printSugarHeader(int growBackInterval, int growBackRate){
		writer.println("\t" + "<GrowBackInterval>" + growBackInterval + "</GrowBackInterval>");
		writer.println("\t" + "<GrowBackRate>" + growBackRate + "</GrowBackRate>");
		writer.println("");
	}
}
