package xml;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class XMLmaker {
	private final static String gol = "game_of_life";
	private final static String seg = "segregation";
	private final static String wator = "wator";
	private final static String fire =  "fire";
	private final static String golTitle = "Game of Life";
	private final static String segTitle = "Segregation";
	private final static String watorTitle = "Wa-Tor World";
	private final static String fireTitle =  "Fire";
	private final static String golAuthor = "John Horton Conway";
	private final static String segAuthor = "Thomas Schelling";
	private final static String watorAuthor = "A.K. Dewdney";
	private final static String fireAuthor =  "Angela B. Shiflet";

	// CHANGE THIS
	private final static String sim = seg;
	private final static int gridx = 400;
	private final static int gridy = 400;
	private final static int cellx = 10;
	private final static int celly = 10;
	private static String title = "";
	private static String author = "";
	private final static String shape = "square";

	// seg specific
	private final static double xProb = 0.3;
	private final static double oProb = 0.3;
	private final static double threshold = 0.3;

	// wator world specific
	private final static double fishProp = 0.55;
	private final static double sharkProp = 0.4;
	private final static int rtShark = 10;
	private final static int rtFish = 5;
	private final static int eShark = 5;
	private final static int eFish = 0;
	private final static int geShark = 5;
	private final static int geFish = 0;

	// fire specific
	private final static double fireProb = 0.5;
	private final static double lightningProb = 0.01;
	private final static double emptyTreeProb = 0.01;


	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		int numCellsX = (int) gridx / cellx;
		int numCellsY = (int) gridy / celly;

		if(sim.equals(gol)){
			title = golTitle;
			author = golAuthor;
		}
		else if(sim.equals(seg)){
			title = segTitle;
			author = segAuthor;
		}
		else if(sim.equals(wator)){
			title = watorTitle;
			author = watorAuthor;
		}
		else if(sim.equals(fire)){
			title = fireTitle;
			author = fireAuthor;
		}

		String fileName = "data/" + sim + ".xml";
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
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

		if(sim.equals(gol)){

		}
		else if(sim.equals(seg)){
			double xNum = 999 * xProb;
			double oNum = 999 * oProb + xNum;
			for(int i = 0; i < numCellsX; i++){
				for(int j = 0; j < numCellsY; j++){
					Random rand = new Random();
					int  n = rand.nextInt(1000);
					String wType = "";

					if(n < xNum){
						wType = "x";
					}
					else if(n > xNum && n < oNum){
						wType = "o";
					}
					else{
						wType = "empty";
					}

					writer.println("\t" + "<cell>");
					writer.println("\t" + "\t" + "<type>" + wType + "</type>");
					writer.println("\t" + "\t" + "<x>" + i + "</x>");
					writer.println("\t" + "\t" + "<y>" + j + "</y>");
					writer.println("\t" + "\t" + "<threshold>" + threshold + "</threshold>");
					writer.println("\t" + "</cell>");
					writer.println("");
				}
			}
		}
		else if(sim.equals(wator)){
			double fishNum = 999 * fishProp;
			double sharkNum = 999 * sharkProp + fishNum;
			for(int i = 0; i < numCellsX; i++){
				for(int j = 0; j < numCellsY; j++){
					Random rand = new Random();
					int  n = rand.nextInt(1000);
					String wType = "";
					int rT = 0;
					int gE = 0;
					int e = 0;

					if(n < fishNum){
						wType = "fish";
						rT = rtFish;
						gE = geFish;
						e = eFish;

					}
					else if(n > fishNum && n < sharkNum){
						wType = "shark";
						rT = rtShark;
						gE = geShark;
						e = eShark;
					}
					else{
						wType = "empty";
					}

					writer.println("\t" + "<cell>");
					writer.println("\t" + "\t" + "<type>" + wType + "</type>");
					writer.println("\t" + "\t" + "<x>" + i + "</x>");
					writer.println("\t" + "\t" + "<y>" + j + "</y>");
					writer.println("\t" + "\t" + "<reproductionThreshold>" + rT + "</reproductionThreshold>");
					writer.println("\t" + "\t" + "<gainedEnergy>" + gE + "</gainedEnergy>");
					writer.println("\t" + "\t" + "<energy>" + e + "</energy>");
					writer.println("\t" + "</cell>");
					writer.println("");
				}
			}
		}
		else if(sim.equals(fire)){	
			writer.println("\t" + "<fireProb>" + fireProb + "</fireProb>");
			writer.println("\t" + "<lightningProb>" + lightningProb + "</lightningProb>");
			writer.println("\t" + "<emptyTreeProb>" + emptyTreeProb + "</emptyTreeProb>");
			writer.println("");

			for(int i = 0; i < numCellsX; i++){
				writer.println("\t" + "<cell>");
				writer.println("\t" + "\t" + "<type>empty</type>");
				writer.println("\t" + "\t" + "<x>" + i + "</x>");
				writer.println("\t" + "\t" + "<y>" + 0 + "</y>");
				writer.println("\t" + "</cell>");
				writer.println("");
				writer.println("\t" + "<cell>");
				writer.println("\t" + "\t" + "<type>empty</type>");
				writer.println("\t" + "\t" + "<x>" + i + "</x>");
				writer.println("\t" + "\t" + "<y>" + (numCellsY-1) + "</y>");
				writer.println("\t" + "</cell>");
				writer.println("");
			}

			for(int i = 0; i < numCellsY; i++){
				writer.println("\t" + "<cell>");
				writer.println("\t" + "\t" + "<type>empty</type>");
				writer.println("\t" + "\t" + "<x>" + 0 + "</x>");
				writer.println("\t" + "\t" + "<y>" + i + "</y>");
				writer.println("\t" + "</cell>");
				writer.println("");
				writer.println("\t" + "<cell>");
				writer.println("\t" + "\t" + "<type>empty</type>");
				writer.println("\t" + "\t" + "<x>" + (numCellsX-1) + "</x>");
				writer.println("\t" + "\t" + "<y>" + i+ "</y>");
				writer.println("\t" + "</cell>");
				writer.println("");
			}

			writer.println("\t" + "<cell>");
			writer.println("\t" + "\t" + "<type>fire</type>");
			int centerX = (int) (numCellsX-1)/2;
			int centerY = (int) (numCellsY-1)/2;
			writer.println("\t" + "\t" + "<x>" + centerX + "</x>");
			writer.println("\t" + "\t" + "<y>" + centerY + "</y>");
			writer.println("\t" + "</cell>");


		}
		else{
			System.out.println("Error, invalid sim type");
		}
		writer.print("</simulation>");
		writer.close();
		System.out.println("Done.");
	}
}
