package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import xml.XMLmaker;

public class SegXMLmaker extends XMLmaker{
	private final static String TYPE = "segregation";
	private final static String SEG_TITLE = "Segregation";
	private final static String SEG_AUTHOR = "Thomas Schelling";

	// seg specific
	private double prob_x = 0.3;
	private double prob_o = 0.3;
	private double threshold = 0.3;

	public SegXMLmaker(String file, String shape, int gx, int gy, int cx, int cy, double probx, double probo, double thresh) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, SEG_TITLE, SEG_AUTHOR, shape, gx, gy, cx, cy);
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

	private void printSegCell(String type, int i, int j){
		printCellHeader(type, i, j);
		writer.println("\t" + "\t" + "<threshold>" + threshold + "</threshold>");
		printCellFooter();
	}
}
