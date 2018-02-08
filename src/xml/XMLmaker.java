package xml;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * XMLmaker - creates XML files for use with project
 * @author Marcus Oertle
 * 
 */

public class XMLmaker {
	private final static String GOL = "game_of_life";
	private final static String SEG = "segregation";
	private final static String GOL_TITLE = "Game of Life";
	private final static String SEG_TITLE = "Segregation";

	private final static String GOL_AUTHOR = "John Horton Conway";
	private final static String SEG_AUTHOR = "Thomas Schelling";


	// CHANGE THIS
	protected final static int PROB = 999;

	// INITIALIZED INSTANCE VARIABLES
	private String title;
	private String author;
	protected PrintWriter writer;
	protected int numCellsX;
	protected int numCellsY;
	private int gridx;
	private int gridy;
	private int cellx;
	private int celly;
	private String shape;
	private String fileName;

	// seg specific
	private final static double PROB_X = 0.3;
	private final static double PROB_O = 0.3;
	private final static double THRESHOLD = 0.3;

	public XMLmaker(String file, String sim, String t, String a, String s, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		title = t;
		author = a;
		shape = s;
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly;
		fileName = "data/" + file + ".xml";

		// write header to file
		writer = new PrintWriter(fileName, "UTF-8");
		writer.println("<" + '?' + "xml version" + '=' + '"'+ "1.0" + '"' + " encoding=" + '"' + "UTF-8" + '"' + '?' + ">");
		writer.println("<simulation>");
		writer.println("\t" + "<name>" + sim + "</name>");
		writer.println("\t" + "<title>" + title + "</title>");
		writer.println("\t" + "<author>" + author + "</author>");
		writer.println("\t" + "<cell_shape>" + shape + "</cell_shape>");
		writer.println("\t" + "<cell_xsize>" + cellx + "</cell_xsize>");
		writer.println("\t" + "<cell_ysize>" + celly + "</cell_ysize>");
		writer.println("\t" + "<grid_x>" + gridx + "</grid_x>");
		writer.println("\t" + "<grid_y>" + gridy + "</grid_y>");

//		// create cells based on simulation type and parameters
//		if(sim.equals(GOL)){
//		}
//		if(sim.equals(SEG)){
//			double xNum = PROB * PROB_X;
//			double oNum = PROB * PROB_O + xNum;
//			for(int i = 0; i < numCellsX; i++){
//				for(int j = 0; j < numCellsY; j++){
//					Random rand = new Random();
//					int  n = rand.nextInt(PROB+1);
//					String wType = "";
//
//					if(n < xNum){
//						wType = "x";
//					}
//					else if(n > xNum && n < oNum){
//						wType = "o";
//					}
//					else{
//						wType = "empty";
//					}
//
//					printSegCell(wType, i, j);
//				}
//			}
//		}
//		else{
//			System.out.println("Error, invalid sim type");
//		}
	}

	protected void closeWriter(){
		writer.print("</simulation>");
		writer.close();
		System.out.println("Done.");
	}
	
	protected void printCellHeader(String type, int i, int j){
		writer.println("\t" + "<cell>");
		writer.println("\t" + "\t" + "<type>" + type + "</type>");
		writer.println("\t" + "\t" + "<x>" + i + "</x>");
		writer.println("\t" + "\t" + "<y>" + j + "</y>");
	}

	protected void printCellFooter(){
		writer.println("\t" + "</cell>");
		writer.println("");
	}

	private void printSegCell(String type, int i, int j){
		printCellHeader(type, i, j);
		writer.println("\t" + "\t" + "<threshold>" + THRESHOLD + "</threshold>");
		printCellFooter();
	}

	protected void printCell(String type, int i, int j){
		printCellHeader(type, i, j);
		printCellFooter();
	}
}
