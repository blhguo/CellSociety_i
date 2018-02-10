package xml.savers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cell.Cell;
import grid.GOLSimGrid;
import xml.XMLmaker;

public class GOLXMLsaver extends XMLmaker{
	private final static String FILE = "data/gol_saved.xml";
	private final static String TYPE = "game_of_life";
	private final static String TITLE = "Game of Life";
	private final static String AUTHOR = "John Horton Conway";
	
	public GOLXMLsaver(GOLSimGrid grid) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, TITLE, AUTHOR);
//		shape = grid.getShape();
//		gridx = grid.getGridX();
//		gridy = grid.getGridY();
//		cellx = grid.getCellX();
//		celly = grid.getCellY();
		shape = "square";
		gridx = 400;
		gridy = 400;
		cellx = 40;
		celly = 40;
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly;
		printFileHeader2();
		Cell[][] cellarray = grid.getCellArray();
		String type;
		for(int i = 0; i < cellarray[0].length; i++){
			for(int j = 0; j < cellarray[1].length; j++){
				//type = cellArray[i]][j].getType();
				type = "alive";
				if(!type.equals("empty")){
					printCell(type, i, j);
				}
			}
		}
	}
}
