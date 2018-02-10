package xml;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import grid.Grid;

/**
 * XMLmaker - creates XML files for use with project
 * @author Marcus Oertle
 * 
 */

public class XMLmaker {

	// FINALS
	protected final static int PROB = 999;

	// INITIALIZED INSTANCE VARIABLES
	private String sim;
	private String title;
	private String author;
	protected PrintWriter writer;
	protected int numCellsX;
	protected int numCellsY;
	protected int gridx;
	protected int gridy;
	protected int cellx;
	protected int celly;
	protected String shape;
	private String fileName;

	public XMLmaker(String file, String simu, String t, String a, String s, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		sim = simu;
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

		printFileHeader();
		printFileHeader2();
	}

	public XMLmaker(String file, String simu, String t, String a) throws FileNotFoundException, UnsupportedEncodingException{
		sim = simu;
		title = t;
		author = a;
		fileName = "data/" + file + ".xml";
		printFileHeader();
	}

	private void printFileHeader() throws FileNotFoundException, UnsupportedEncodingException{
		// write header to file
		writer = new PrintWriter(fileName, "UTF-8");
		writer.println("<" + '?' + "xml version" + '=' + '"'+ "1.0" + '"' + " encoding=" + '"' + "UTF-8" + '"' + '?' + ">");
		writer.println("<simulation>");
		writer.println("\t" + "<name>" + sim + "</name>");
		writer.println("\t" + "<title>" + title + "</title>");
		writer.println("\t" + "<author>" + author + "</author>");
	}
	
	protected void printFileHeader2(){
		writer.println("\t" + "<cell_shape>" + shape + "</cell_shape>");
		writer.println("\t" + "<cell_xsize>" + cellx + "</cell_xsize>");
		writer.println("\t" + "<cell_ysize>" + celly + "</cell_ysize>");
		writer.println("\t" + "<grid_x>" + gridx + "</grid_x>");
		writer.println("\t" + "<grid_y>" + gridy + "</grid_y>");
	}

	protected void closeWriter(){
		writer.print("</simulation>");
		writer.close();
		//System.out.println("Done.");
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

	protected void printCell(String type, int i, int j){
		printCellHeader(type, i, j);
		printCellFooter();
	}
}
