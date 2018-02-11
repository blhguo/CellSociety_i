package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import grid.GOLSimGrid;
import grid.SegregationSimGrid;
import xml.XMLmaker;

public class SegXMLmaker extends XMLmaker{
	private final static String FILE = "data/seg_saved.xml";
	private final static String TYPE = "segregation";
	private final static String SEG_TITLE = "Segregation";
	private final static String SEG_AUTHOR = "Thomas Schelling";

	// seg specific
	private double prob_x = 0.3;
	private double prob_o = 0.3;
	private double threshold = 0.3;

	public SegXMLmaker(String file, String shape, String nT, String eT, int gx, int gy, int cx, int cy, double probx, double probo, double thresh) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, SEG_TITLE, SEG_AUTHOR, shape, nT, eT, gx, gy, cx, cy);
		prob_x = probx;
		prob_o = probo;
		threshold = thresh;
		double xNum = PROB * prob_x;
		double oNum = PROB * prob_o + xNum;
		for(int i = 0; i < numCellsX; i++){
			for(int j = 0; j < numCellsY; j++){
				Random rand = new Random();
				int  n = rand.nextInt(PROB+1);
				String wType = "";

				if(n < xNum){
					wType = "x";
				}
				else if(n > xNum && n < oNum){
					wType = "o";
				}
				else{
					continue;
				}

				printSegCell(wType, i, j);
			}
		}
		closeWriter();
	}

	public SegXMLmaker(SegregationSimGrid grid, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, SEG_TITLE, SEG_AUTHOR);
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		shape = grid.getShape();
		//neighbourType = grid.getNeighborArrangement();
		//edgeType = grid.getEdgeType();
		neighbourType = "all";
		edgeType = "finite";
		double[][] gThreshold= grid.getThreshold();
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly; 
		printFileHeader2();
		String[][] cellArray = grid.getArray();
		String type;
		for(int i = 0; i < cellArray[0].length; i++){
			for(int j = 0; j < cellArray[1].length; j++){
				type = cellArray[i][j];
				threshold = gThreshold[i][j];
				if(!type.equals("empty")){
					printSegCell(type, i, j);
				}
			}
		}
		closeWriter();
	}

	private void printSegCell(String type, int i, int j){
		printCellHeader(type, i, j);
		writer.println("\t" + "\t" + "<threshold>" + threshold + "</threshold>");
		printCellFooter();
	}
}
