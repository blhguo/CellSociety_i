package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import xml.XMLmaker;

public class GOLXMLmaker extends XMLmaker{
	private final static String TYPE = "game_of_life";
	private final static String GOL_TITLE = "Game of Life";
	private final static String GOL_AUTHOR = "John Horton Conway";

	// GOL specific
	private double prob_cell = 0.3;

	public GOLXMLmaker(String file, String shape, int gx, int gy, int cx, int cy, double probcell) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, GOL_TITLE, GOL_AUTHOR, shape, gx, gy, cx, cy);
		prob_cell = probcell;
		double cellNum = PROB * prob_cell;
		for(int i = 0; i < numCellsX; i++){
			for(int j = 0; j < numCellsY; j++){
				Random rand = new Random();
				int  n = rand.nextInt(PROB+1);
				String wType = "";

				if(n < cellNum){
					wType = "alive";
					printCell(wType, i, j);
				}
			}
		}
		closeWriter();
	}
}
