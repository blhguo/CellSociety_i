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
	private final static String fireAuthor =  "?";

	// CHANGE THIS
	private final static String sim = wator;
	private final static int gridx = 400;
	private final static int gridy = 400;
	private final static int cellx = 10;
	private final static int celly = 10;
	private static String title = "";
	private static String author = "";
	private final static String shape = "square";

	// wator world specific
	private final static double fishProp = 0.3;
	private final static double sharkProp = 0.3;
	private final static int rtShark = 5;
	private final static int rtFish = 5;
	private final static int eShark = 5;
	private final static int eFish = 5;
	private final static int geShark = 1;
	private final static int geFish = 0;


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
		
		String fileName = sim + ".txt";
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println("<" + '?' + "xml version" + '=' + '"'+ "1.0" + '"' + " encoding=" + '"' + "UTF-8" + '"' + '?' + ">");
		writer.println("<simulation>");
		writer.println("\t" + "<name>" + sim + "</name>");
		writer.println("\t" + "<title>" + title + "</title>");
		writer.println("\t" + "<author>" + author + "</author>");
		writer.println("\t" + "<cell_shape>" + shape + "</cell_shape>");
		writer.println("\t" + "<cell_xsize>" + cellx + "</cell_xsize>");
		writer.println("\t" + "<cell_ysize>" + celly + "</cell_ysize>");
		writer.println("\t" + "<gird_x>" + gridx + "</grid_x>");
		writer.println("\t" + "<gird_y>" + gridy + "</grid_y>");
		
		if(sim.equals(gol)){

		}
		else if(sim.equals(seg)){

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
				}
			}
		}
		else if(sim.equals(fire)){			
			for(int i = 0; i < numCellsX; i++){
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + i + "</x>");
				System.out.println("\t" + "\t" + "<y>" + 0 + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + i + "</x>");
				System.out.println("\t" + "\t" + "<y>" + (numCellsY-1) + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
			}

			for(int i = 0; i < numCellsY; i++){
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + 0 + "</x>");
				System.out.println("\t" + "\t" + "<y>" + i + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + (numCellsX-1) + "</x>");
				System.out.println("\t" + "\t" + "<y>" + i+ "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
			}

			System.out.println("\t" + "<cell>");
			System.out.println("\t" + "\t" + "<type>fire</type>");
			int centerX = (int) (numCellsX-1)/2;
			int centerY = (int) (numCellsY-1)/2;
			System.out.println("\t" + "\t" + "<x>" + centerX + "</x>");
			System.out.println("\t" + "\t" + "<y>" + centerY + "</y>");
			System.out.println("\t" + "</cell>");


		}
		else{
			System.out.println("Error, invalid sim type");
		}
		writer.print("</simulation>");
		writer.close();
	}
}
