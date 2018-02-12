package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import cell.Cell;
import grid.GOLSimGrid;
import xml.XMLmaker;

/**
 * GOLXMLmaker - creates GOL XML files for use with project
 * @author marcusoertle
 *
 */

public class GOLXMLmaker extends XMLmaker{
	private final static String FILE = "gol_saved";
	private final static String TYPE = "game_of_life";
	private final static String GOL_TITLE = "Game of Life";
	private final static String GOL_AUTHOR = "John Horton Conway";

	// GOL specific
	private double prob_cell = 0.3;

	public GOLXMLmaker(String file, String shape, String nT, String eT, int gx, int gy, int cx, int cy, double probcell) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, GOL_TITLE, GOL_AUTHOR, shape, nT, eT, gx, gy, cx, cy);
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
	
	/**
	 * Alternative inputs for GOLXMLmaker
	 * @param grid
	 * @param gx
	 * @param gy
	 * @param cx
	 * @param cy
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public GOLXMLmaker(GOLSimGrid grid, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, GOL_TITLE, GOL_AUTHOR);
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		shape = grid.getShape();
		neighbourType = grid.getNeighborArrangement();
		edgeType = grid.getEdgeType();
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly;
		printFileHeader2();
		String[][] cellArray = grid.getArray();
		String type;
		for(int i = 0; i < cellArray[0].length; i++){
			for(int j = 0; j < cellArray[1].length; j++){
				type = cellArray[i][j];
				if(!type.equals("dead")){
					printCell(type, i, j);
				}
			}
		}
		closeWriter();
	}
}
